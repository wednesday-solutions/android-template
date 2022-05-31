package com.wednesday.template.service.openWeather.currentWeather.local

import androidx.annotation.Keep

@Keep
data class LocalCurrentWeatherSys(
    val country: String,
    val sunrise: Int,
    val sunset: Int,
)
