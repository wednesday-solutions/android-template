package com.wednesday.template.domain.weather

import com.wednesday.template.domain.date.Date

data class Weather(
    val title: String,
    val woeid: Int,
    val minTemp: Double,
    val maxTemp: Double,
    val temp: Double,
    val date: Date
)
