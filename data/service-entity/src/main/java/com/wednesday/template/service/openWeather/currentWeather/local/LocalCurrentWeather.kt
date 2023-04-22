package com.wednesday.template.service.openWeather.currentWeather.local

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Keep
@Entity(tableName = "current_weather")
data class LocalCurrentWeather(
    val base: String,
    @Embedded(prefix = "clouds_")
    val clouds: LocalCurrentWeatherClouds,
    val cod: Int,
    @Embedded(prefix = "coord_")
    val coord: LocalCurrentWeatherCoord,
    val dt: Int,
    @PrimaryKey
    val id: Int,
    @Embedded(prefix = "main_")
    val main: LocalCurrentWeatherMain,
    val name: String,
    @Embedded(prefix = "sys_")
    val sys: LocalCurrentWeatherSys,
    val timezone: Int,
    val visibility: Int,
    @Embedded(prefix = "weather_")
    val weather: LocalCurrentWeatherWeather,
    @Embedded(prefix = "wind_")
    val wind: LocalCurrentWeatherWind,
    val updatedAt: Date
)
