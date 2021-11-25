package com.wednesday.template.presentation.weather.search

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.weather.UICity

sealed interface SearchScreenIntent : Intent {

    data class SearchCities(
        val city: String
    ) : SearchScreenIntent

    data class SearchCitiesModel(
        val city: UICity
    ) : SearchScreenIntent
}
