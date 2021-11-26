package com.wednesday.template.presentation.weather.start.list

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.FavoriteCity
import com.wednesday.template.resources.databinding.FavoriteCityItemListBinding
import kotlinx.coroutines.channels.Channel

class FavoriteCityListViewHolder(private val binding: FavoriteCityItemListBinding):BaseViewHolder<FavoriteCity>(binding) {
    override fun onSetupIntents(intentChannel: Channel<Intent>) {
        TODO("Not yet implemented")
    }
    
    override fun onBindInternal() {
        TODO("Not yet implemented")
    }
}