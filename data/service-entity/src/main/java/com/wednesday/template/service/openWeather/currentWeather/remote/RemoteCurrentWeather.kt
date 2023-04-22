package com.wednesday.template.service.openWeather.currentWeather.remote

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RemoteCurrentWeather(
    @SerialName("base")
    val base: String,
    @SerialName("clouds")
    val clouds: RemoteCurrentWeatherClouds,
    @SerialName("cod")
    val cod: Int,
    @SerialName("coord")
    val coord: RemoteCurrentWeatherCoord,
    @SerialName("dt")
    val dt: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val main: RemoteCurrentWeatherMain,
    @SerialName("name")
    val name: String,
    @SerialName("sys")
    val sys: RemoteCurrentWeatherSys,
    @SerialName("timezone")
    val timezone: Int,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("weather")
    val weather: List<RemoteCurrentWeatherWeather>,
    @SerialName("wind")
    val wind: RemoteCurrentWeatherWind
)
