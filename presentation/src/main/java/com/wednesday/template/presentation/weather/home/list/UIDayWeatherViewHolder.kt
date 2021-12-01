package com.wednesday.template.presentation.weather.home.list

import com.wednesday.template.presentation.base.extensions.setUIText
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIDayWeather
import com.wednesday.template.resources.databinding.ItemDayWeatherBinding
import kotlinx.coroutines.channels.Channel

class UIDayWeatherViewHolder(private val binding: ItemDayWeatherBinding) : BaseViewHolder<UIDayWeather>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) = Unit

    override fun onBindInternal() = binding.run {

        compareAndSet({ date }) {
            textViewDayWeatherDate.setUIText(it)
        }

        compareAndSet({ currentTemp }) {
            textViewDayWeatherTemp.setUIText(it)
        }
    }
}
