package com.wednesday.template.home.presentation.list

import android.view.ViewGroup
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIDayWeather
import com.wednesday.template.resources.databinding.ItemDayWeatherBinding

class UIDayWeatherRenderer : ListItemRenderer<UIDayWeather>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIDayWeather> {
        return UIDayWeatherViewHolder(
            binding = viewGroup bind ItemDayWeatherBinding::inflate
        )
    }
}
