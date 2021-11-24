package com.wednesday.template.presentation.base.list.viewholder

import com.wednesday.template.presentation.base.asString
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.UIListUIText
import com.wednesday.template.resources.databinding.ItemListTextBinding
import kotlinx.coroutines.channels.Channel

class UIListUITextViewHolder(private val binding: ItemListTextBinding) :
    BaseViewHolder<UIListUIText>(binding) {
    override fun onSetupIntents(intentChannel: Channel<Intent>) {
        /* no-op */
    }

    override fun onBindInternal() = binding.run {
        compareAndSet({ text }) {
            textViewListItem.text = it.asString(root.context)
        }
    }
}
