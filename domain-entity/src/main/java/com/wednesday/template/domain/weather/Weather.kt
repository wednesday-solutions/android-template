package com.wednesday.template.domain.weather

import com.wednesday.template.domain.date.Date

data class Weather(
    val title: String,
    val woeid: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val temp: Int,
    val date: Date
)
