package com.wednesday.template.repo.weather.models

import com.wednesday.template.domain.weather.Weather

val weather = Weather(
    title = "${localWeather.name}, ${localWeather.sys.country}",
    description = localWeather.weather.description,
    lat = localWeather.coord.lat,
    lon = localWeather.coord.lon,
    minTemp = localWeather.main.tempMin,
    maxTemp = localWeather.main.tempMax,
    temp = localWeather.main.temp,
    feelsLike = localWeather.main.feelsLike,
    iconUrl = "https://openweathermap.org/img/wn/${localWeather.weather.icon}@4x.png",
)
