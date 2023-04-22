package com.wednesday.template.repo.weather.models

import com.wednesday.template.domain.date.JavaDate
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeather
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherClouds
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherCoord
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherMain
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherSys
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherWeather
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherWind
import java.time.Instant

val localWeather = LocalCurrentWeather(
    base = "base 1",
    clouds = LocalCurrentWeatherClouds(all = 4),
    cod = 10,
    coord = LocalCurrentWeatherCoord(lat = 10.10, lon = 30.55),
    dt = 10,
    id = 100,
    main = LocalCurrentWeatherMain(
        feelsLike = 30.45,
        humidity = 45,
        pressure = 100,
        temp = 26.03,
        tempMin = 22.33,
        tempMax = 32.0
    ),
    name = "name 1",
    sys = LocalCurrentWeatherSys(country = "country 1", sunrise = 10, sunset = 20),
    timezone = 10,
    visibility = 98,
    weather = LocalCurrentWeatherWeather(
        description = "description 1",
        icon = "01d",
        id = 50,
        main = "main 1"
    ),
    wind = LocalCurrentWeatherWind(deg = 23, speed = 45.93),
    updatedAt = JavaDate.from(Instant.EPOCH)
)
