package com.wednesday.template.presentation.base.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import kotlinx.coroutines.channels.Channel
import java.lang.Exception
import kotlin.reflect.KClass

class ListAdapter(
    private val intentChannel: Channel<Intent>,
    private val renderers: List<Pair<KClass<*>, ListItemRenderer<UIListItemBase>>>
) : RecyclerView.Adapter<BaseViewHolder<UIListItemBase>>(), BaseAdapter {

    private val _items = mutableListOf<UIListItemBase>()
    override val items = _items

    override fun updateData(newItems: List<UIListItemBase>) {
        _items.clear()
        _items.addAll(newItems)
    }

    override fun getItemViewType(position: Int): Int {
        val item = _items[position]
        val index = renderers.indexOfFirst { (kClass, _) -> kClass == item::class }
        if (index == -1) {
            throw IllegalStateException("No renderer found for type ${item::class.qualifiedName}")
        }
        return index
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<UIListItemBase> {
        return renderers[viewType].second.getViewHolder(parent).also {
            it.intentChannel = intentChannel
            it.onSetupIntents(intentChannel)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<UIListItemBase>, position: Int) {
        try {
            holder.onBind(_items[position])
        } catch (e: Exception) { /* no-op */ }
    }

    override fun getItemCount(): Int = _items.size
}
