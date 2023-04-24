package com.wednesday.template.repo.weather.models

import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeather
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeatherClouds
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeatherCoord
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeatherMain
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeatherSys
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeatherWeather
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeatherWind

val remoteWeather = RemoteCurrentWeather(
    base = "base 1",
    clouds = RemoteCurrentWeatherClouds(all = 4),
    cod = 10,
    coord = RemoteCurrentWeatherCoord(lat = 10.10, lon = 30.55),
    dt = 10,
    id = 100,
    main = RemoteCurrentWeatherMain(
        feelsLike = 30.45,
        humidity = 45,
        pressure = 100,
        temp = 26.03,
        tempMin = 22.33,
        tempMax = 32.0,
    ),
    name = "name 1",
    sys = RemoteCurrentWeatherSys(country = "country 1", sunrise = 10, sunset = 20),
    timezone = 10,
    visibility = 98,
    weather = listOf(
        RemoteCurrentWeatherWeather(
            description = "description 1",
            icon = "01d",
            id = 50,
            main = "main 1",
        ),
    ),
    wind = RemoteCurrentWeatherWind(deg = 23, speed = 45.93),
)
