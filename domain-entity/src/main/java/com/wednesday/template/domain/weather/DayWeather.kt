package com.wednesday.template.domain.weather

import com.wednesday.template.domain.date.Date

data class DayWeather(
    val minTemp: Int,
    val maxTemp: Int,
    val temp: Int,
    val date: Date,
    val isToday: Boolean
)