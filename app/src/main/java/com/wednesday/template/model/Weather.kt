package com.wednesday.template.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val title: String,
    @SerializedName("consolidated_weather")
    var consolidatedWeathers: List<ConsolidatedWeather>
)
