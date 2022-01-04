package com.wednesday.template.repo.weather.models

import com.wednesday.template.service.weather.RemoteConsolidatedWeather
import com.wednesday.template.service.weather.RemoteWeather
import java.time.Instant
import java.util.Date

val remoteWeather = RemoteWeather(
    title = "title 1",
    consolidatedWeathers = listOf(
        RemoteConsolidatedWeather(
            id = 1.0,
            minTemp = 22.0,
            maxTemp = 32.0,
            theTemp = 30.0,
            date = Date.from(Instant.EPOCH)
        )
    )
)
