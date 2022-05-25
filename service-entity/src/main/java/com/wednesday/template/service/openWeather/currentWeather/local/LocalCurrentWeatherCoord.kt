package com.wednesday.template.service.openWeather.currentWeather.local


import androidx.annotation.Keep

@Keep
data class LocalCurrentWeatherCoord(
    val lat: Double,
    val lon: Double
)