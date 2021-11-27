package com.wednesday.template.presentation.weather.home.list

import com.wednesday.template.presentation.base.extensions.setUIText
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIWeather
import com.wednesday.template.resources.databinding.ItemWeatherBinding
import kotlinx.coroutines.channels.Channel

class UIWeatherViewHolder(private val binding: ItemWeatherBinding) : BaseViewHolder<UIWeather>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) = Unit

    override fun onBindInternal() = binding.run {

        compareAndSet({ title }) {
            cityName.setUIText(it)
        }

        compareAndSet({ currentTemp }) {
            cityTemp.setUIText(it)
        }
    }
}
