package com.wednesday.template.presentation.weather.search

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.resources.databinding.CityItemListBinding
import kotlinx.coroutines.channels.Channel

class UICityListViewHolder(private val binding: CityItemListBinding) :
    BaseViewHolder<UICity>(binding) {
    
    override fun onSetupIntents(intentChannel: Channel<Intent>) = with(binding) {
        addCityImageButtonListItem.setOnClickListener {
            val value = SearchScreenIntent.SearchCitiesModel(
                item.cityId,
                item.title,
                item.locationType,
                item.latitude
            )
            intentChannel.trySend(value)
        }
    }
    
    override fun onBindInternal() = binding.run {
        compareAndSet({ title }) {
            cityTextViewListItem.text = it
        }
        compareAndSet({ latitude }) {
            latitudeTextViewListItem.text = it
        }
    }
}
