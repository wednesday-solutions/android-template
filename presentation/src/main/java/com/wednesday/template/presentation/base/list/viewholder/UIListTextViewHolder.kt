package com.wednesday.template.presentation.base.list.viewholder

import kotlinx.coroutines.channels.Channel
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.databinding.ItemListTextBinding
import com.wednesday.template.presentation.base.list.UIListText

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
