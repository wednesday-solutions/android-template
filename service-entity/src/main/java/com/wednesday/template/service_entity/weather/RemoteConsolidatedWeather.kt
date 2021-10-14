package com.wednesday.template.service_entity.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteConsolidatedWeather(
    @SerialName("id")
    val id: Double,
    @SerialName("weather_state_name")
    val weatherStateName: String,
    @SerialName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerialName("wind_direction_compass")
    val windDirectionCompass: String,
    @SerialName("min_temp")
    val minTemp: Double,
    @SerialName("max_temp")
    val maxTemp: Double,
    @SerialName("the_temp")
    val theTemp: Double,
    @SerialName("wind_speed")
    val windSpeed: Double,
    @SerialName("wind_direction")
    val windDirection: Double,
    @SerialName("air_pressure")
    val airPressure: Double,
    @SerialName("humidity")
    val humidity: Double,
    @SerialName("visibility")
    val visibility: Double,
    @SerialName("predictability")
    val predictability: Double
) {
//    val weatherCondition: WeatherCondition
//        get() = WeatherCondition.weatherConditionFromAbbreviation(weatherStateAbbr)
//
//    val weatherConditionImageResourceId: Int
//        get() = weatherCondition.weatherConditionImageResouceId()
}
