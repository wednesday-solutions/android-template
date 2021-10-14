package com.wednesday.template.service_entity.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeather(
  val title: String,
  @SerialName("consolidated_weather")
  var consolidatedWeathers: List<RemoteConsolidatedWeather>
)
