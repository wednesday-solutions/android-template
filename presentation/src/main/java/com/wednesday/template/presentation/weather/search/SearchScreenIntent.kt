package com.wednesday.template.presentation.weather.search

import com.wednesday.template.presentation.base.intent.Intent

sealed interface SearchScreenIntent : Intent {

    data class SearchCities(
        val city: String
    ) : SearchScreenIntent

    data class SearchCitiesModel(
        val woeid: Int,
        val title: String,
        val locationType: String,
        val latitude:String
    ) : SearchScreenIntent
}
