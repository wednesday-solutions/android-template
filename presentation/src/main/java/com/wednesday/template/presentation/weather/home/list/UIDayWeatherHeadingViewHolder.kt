package com.wednesday.template.presentation.weather.home.list

import com.wednesday.template.presentation.base.extensions.setUIText
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIDayWeatherHeading
import com.wednesday.template.resources.databinding.ItemDayWeatherHeadingBinding
import kotlinx.coroutines.channels.Channel

class UIDayWeatherHeadingViewHolder(
    private val binding: ItemDayWeatherHeadingBinding
) : BaseViewHolder<UIDayWeatherHeading>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) = Unit

    override fun onBindInternal() {

        compareAndSet({ text }) {
            binding.textViewHeading.setUIText(it)
        }
    }

}