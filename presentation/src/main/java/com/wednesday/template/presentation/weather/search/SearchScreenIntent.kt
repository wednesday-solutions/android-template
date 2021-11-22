package com.wednesday.template.presentation.weather.search

import com.wednesday.template.presentation.base.intent.Intent


sealed interface SearchScreenIntent : Intent{

    data class SearchCities(
        val city:String
    ):SearchScreenIntent
}