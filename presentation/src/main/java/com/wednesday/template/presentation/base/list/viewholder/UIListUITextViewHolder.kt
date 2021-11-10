package com.wednesday.template.presentation.base.list.viewholder

import kotlinx.coroutines.channels.Channel
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.UIListUIText
import com.wednesday.template.presentation.databinding.ItemListTextBinding

class UIListUITextViewHolder(private val binding: ItemListTextBinding) :
    BaseViewHolder<UIListUIText>(binding) {
    override fun onSetupIntents(intentChannel: Channel<Intent>) {
        /* no-op */
    }

    override fun onBindInternal() = binding.run {
        compareAndSet({ text }) {
            textViewListItem.text = root.context.getString(it.resId)
        }
    }
}
