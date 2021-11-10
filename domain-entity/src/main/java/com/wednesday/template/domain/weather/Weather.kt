package com.wednesday.template.domain.weather

data class Weather(
    val title: String,
    val weatherList: List<ConsolidatedWeather>
)
