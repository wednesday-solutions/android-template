package com.wednesday.template.service.weather

import androidx.room.Embedded
import androidx.room.Relation

data class LocalCityWithWeather(
    @Embedded
    val city: LocalCity,
    @Relation(parentColumn = "woeid", entityColumn = "cityWoeid")
    val weather: LocalWeather
)
