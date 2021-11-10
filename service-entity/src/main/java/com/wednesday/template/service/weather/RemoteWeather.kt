package com.wednesday.template.service.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeather(
    @SerialName("title")
    val title: String,
    @SerialName("consolidated_weather")
    var consolidatedWeathers: List<RemoteConsolidatedWeather>
)
