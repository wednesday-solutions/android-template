package com.wednesday.template.domain.weather

data class Weather(
    val title: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val temp: Double,
    val feelsLike: Double,
    val iconUrl: String,
)
