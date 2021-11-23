package com.wednesday.template.presentation.weather.search

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.resources.databinding.CityItemListBinding
import kotlinx.coroutines.channels.Channel

class UICityListViewHolder(private val binding: CityItemListBinding) :
    BaseViewHolder<UICity>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) {
    }

    override fun onBindInternal() = binding.run {
        compareAndSet({ title }) {
            cityTextViewListItem.text = it
        }
    }
}
