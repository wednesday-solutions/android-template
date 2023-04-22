package com.wednesday.template.service.openWeather.currentWeather.local

import androidx.annotation.Keep

@Keep
data class LocalCurrentWeatherWeather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
