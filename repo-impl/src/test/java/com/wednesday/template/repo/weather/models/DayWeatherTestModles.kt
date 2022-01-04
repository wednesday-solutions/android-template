package com.wednesday.template.repo.weather.models

import com.wednesday.template.domain.date.Date
import com.wednesday.template.domain.weather.DayWeather

val dayWeatherMapperFromLocalDayWeather = DayWeather(
    minTemp = localDayWeather.minTemp,
    maxTemp = localDayWeather.maxTemp,
    temp = localDayWeather.temp,
    date = Date(1, 1, 1970),
    isToday = false
)
