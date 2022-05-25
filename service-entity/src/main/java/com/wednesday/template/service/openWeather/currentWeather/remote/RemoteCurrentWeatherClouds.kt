package com.wednesday.template.service.openWeather.currentWeather.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class RemoteCurrentWeatherClouds(
    @SerialName("all")
    val all: Int
)