package com.wednesday.template.repo.weather.models

import com.wednesday.template.domain.weather.Weather

val weatherMappedFromLocalCityWithWeather = Weather(
    title = localWeather.title,
    woeid = localWeather.cityWoeid,
    dayWeatherList = listOf(dayWeatherMapperFromLocalDayWeather)
)
