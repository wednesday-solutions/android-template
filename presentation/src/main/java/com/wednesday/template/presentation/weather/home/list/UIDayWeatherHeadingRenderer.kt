package com.wednesday.template.presentation.weather.home.list

import android.view.ViewGroup
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIDayWeatherHeading
import com.wednesday.template.resources.databinding.ItemDayWeatherHeadingBinding

class UIDayWeatherHeadingRenderer : ListItemRenderer<UIDayWeatherHeading>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIDayWeatherHeading> {
        return UIDayWeatherHeadingViewHolder(
            binding = viewGroup bind ItemDayWeatherHeadingBinding::inflate
        )
    }
}
