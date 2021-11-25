package com.wednesday.template.presentation.weather.start

import com.wednesday.template.presentation.base.intent.Intent

interface StartScreenIntent : Intent{
    
    data class FavoriteCities(
        val city: String
    ): StartScreenIntent
}