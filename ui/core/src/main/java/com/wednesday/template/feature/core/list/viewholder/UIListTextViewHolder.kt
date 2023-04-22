package com.wednesday.template.presentation.base.list.viewholder

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.UIListText
import com.wednesday.template.resources.databinding.ItemListTextBinding
import kotlinx.coroutines.channels.Channel

class UIListTextViewHolder(private val binding: ItemListTextBinding) :
    BaseViewHolder<UIListText>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) {
        /* no-op */
    }

    override fun onBindInternal() = binding.run {
        compareAndSet({ text }) {
            textViewListItem.text = it
        }
    }
}
