package com.wednesday.template.service.weather

import com.wednesday.template.service.RemoteDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class RemoteConsolidatedWeather(
    @SerialName("id")
    val id: Double,
    @SerialName("min_temp")
    val minTemp: Double,
    @SerialName("max_temp")
    val maxTemp: Double,
    @SerialName("the_temp")
    val theTemp: Double,
    @SerialName("applicable_date")
    @Serializable(RemoteDateSerializer::class)
    val date: Date
)
