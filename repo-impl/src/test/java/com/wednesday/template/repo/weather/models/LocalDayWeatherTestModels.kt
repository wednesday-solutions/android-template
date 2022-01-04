package com.wednesday.template.repo.weather.models

import com.wednesday.template.service.weather.LocalDayWeather
import java.time.Instant
import java.util.Date

val localDayWeather = LocalDayWeather(
    id = 1,
    cityWoeid = 1,
    temp = 30,
    minTemp = 22,
    maxTemp = 32,
    date = Date.from(Instant.EPOCH)
)
