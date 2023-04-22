package com.wednesday.template.home.presentation.list

import com.wednesday.template.presentation.base.extensions.setUIText
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.home.presentation.HomeScreenIntent
import com.wednesday.template.resources.databinding.ItemSearchCityPlaceholderBinding
import kotlinx.coroutines.channels.Channel

class UISearchCitiesPlaceholderViewHolder(private val binding: ItemSearchCityPlaceholderBinding) :
    BaseViewHolder<UISearchCitiesPlaceholder>(binding) {

    override fun onSetupIntents(intentChannel: Channel<Intent>) = binding.run {
        buttonSearch.setOnClickListener {
            intentChannel.trySend(HomeScreenIntent.Search)
        }
    }

    override fun onBindInternal() = binding.run {

        compareAndSet({ text }) {
            textViewPlaceholder.setUIText(it)
        }
    }
}
