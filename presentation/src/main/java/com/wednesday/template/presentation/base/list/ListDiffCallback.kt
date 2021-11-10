package com.wednesday.template.presentation.base.list

import androidx.recyclerview.widget.DiffUtil
import com.wednesday.template.presentation.base.list.UIListItemBase

class ListDiffCallback(
    private val oldList: List<UIListItemBase>,
    private val newList: List<UIListItemBase>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        return Unit
    }
}
