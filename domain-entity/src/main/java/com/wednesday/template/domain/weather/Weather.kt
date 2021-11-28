package com.wednesday.template.domain.weather

data class Weather(
    val title: String,
    val woeid: Int,
    val dayWeatherList: List<DayWeather>
)
