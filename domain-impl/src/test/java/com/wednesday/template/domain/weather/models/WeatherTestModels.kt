package com.wednesday.template.domain.weather.models

import com.wednesday.template.domain.date.Date
import com.wednesday.template.domain.weather.DayWeather
import com.wednesday.template.domain.weather.Weather

val weather = Weather(
    title = "title 1",
    woeid = 1,
    dayWeatherList = listOf(
        DayWeather(
            minTemp = 22,
            maxTemp = 32,
            temp = 30,
            date = Date(1, 1, 1970),
            isToday = false
        )
    )
)