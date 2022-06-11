package com.wednesday.template.presentation.base.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIList(
    val items: List<UIListItemBase>
) : Parcelable {

    constructor(vararg items: UIListItemBase) : this(mutableListOf(*items))

    operator fun get(index: Int) = items[index]

    fun copyReplacing(index: Int, item: UIListItemBase): UIList {
        val newItems = ArrayList<UIListItemBase>(items.size)
        (0 until index).forEach {
            newItems.add(items[it])
        }
        newItems.add(item)
        (index + 1 until size()).forEach {
            newItems.add(items[it])
        }
        return UIList(newItems)
    }

    fun copyReplacingFrom(fromIndex: Int, items: List<UIListItemBase>): UIList {
        val newItems = ArrayList<UIListItemBase>(fromIndex + items.size)
        (0 until fromIndex).forEach {
            newItems.add(this.items[it])
        }
        newItems.addAll(items)
        return UIList(newItems)
    }

    inline fun <reified T : UIListItemBase> update(block: T.(index: Int) -> T): UIList {
        val newItems = items.mapIndexed { index, uiListItemBase ->
            if (uiListItemBase is T) block(uiListItemBase, index)
            else uiListItemBase
        }
        return UIList(newItems)
    }

    fun size() = items.size
}
