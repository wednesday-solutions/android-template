package com.wednesday.template.domain.weather

data class ConsolidatedWeather(
    val id: Double,
    val weatherStateName: String,
    val weatherStateAbbr: String,
    val windDirectionCompass: String,
    val minTemp: Double,
    val maxTemp: Double,
    val theTemp: Double,
    val windSpeed: Double,
    val windDirection: Double,
    val airPressure: Double,
    val humidity: Double,
    val visibility: Double,
    val predictability: Double
)
