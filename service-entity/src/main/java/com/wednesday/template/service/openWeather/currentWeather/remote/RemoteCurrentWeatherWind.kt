package com.wednesday.template.service.openWeather.currentWeather.remote


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RemoteCurrentWeatherWind(
    @SerialName("deg")
    val deg: Int,
    @SerialName("speed")
    val speed: Double
)