package com.wednesday.template.presentation.weather.home.list

import android.view.ViewGroup
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIWeather
import com.wednesday.template.resources.databinding.ItemWeatherBinding

class UIWeatherRenderer : ListItemRenderer<UIWeather, Intent>() {
    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIWeather> {
        return UIWeatherViewHolder(
            binding = viewGroup bind ItemWeatherBinding::inflate
        )
    }
}
