package com.wednesday.template.presentation.weather.start

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.screen.Screen

interface StartScreenIntent : Intent {

    data class FavoriteCities(
        val city: String
    ) : StartScreenIntent

    data class OnClickSearchCities(
        val screen: Screen
    ) : StartScreenIntent
}
