package com.wednesday.template.service.openWeather.currentWeather.local

import androidx.annotation.Keep

@Keep
data class LocalCurrentWeatherWind(
    val deg: Int,
    val speed: Double
)
