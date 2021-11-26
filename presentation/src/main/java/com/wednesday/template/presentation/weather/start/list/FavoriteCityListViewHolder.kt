package com.wednesday.template.presentation.weather.start.list

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIFavoriteCity
import com.wednesday.template.resources.databinding.FavoriteCityItemListBinding
import kotlinx.coroutines.channels.Channel

class FavoriteCityListViewHolder(private val binding: FavoriteCityItemListBinding) : BaseViewHolder<UIFavoriteCity>(binding) {
    override fun onSetupIntents(intentChannel: Channel<Intent>) {
    }

    override fun onBindInternal() = binding.run {
        compareAndSet({ title }) {
            cityName.text = it
        }
        compareAndSet({ currentTemp }) {
            cityTemp.text = it
        }
    }
}
