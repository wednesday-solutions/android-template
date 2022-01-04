package com.wednesday.template.repo.weather.models

import com.wednesday.template.service.weather.LocalCityWithWeather

val localCityWithWeather = LocalCityWithWeather(
    city = localCity,
    weather = localWeather,
    dayWeather = listOf(localDayWeather)
)
