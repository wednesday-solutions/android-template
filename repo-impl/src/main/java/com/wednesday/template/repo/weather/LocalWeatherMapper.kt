package com.wednesday.template.repo.weather

import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.util.Mapper
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeather
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherClouds
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherCoord
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherMain
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherSys
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherWeather
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherWind
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeather
import timber.log.Timber

interface LocalWeatherMapper : Mapper<RemoteCurrentWeather, LocalCurrentWeather>

class LocalWeatherMapperImpl(private val dateRepo: DateRepo) : LocalWeatherMapper {

    override fun map(from: RemoteCurrentWeather): LocalCurrentWeather {
        Timber.tag(TAG).d("map() called with: from = $from")

        val clouds = LocalCurrentWeatherClouds(
            all = from.clouds.all
        )

        val coord = LocalCurrentWeatherCoord(
            lat = from.coord.lat,
            lon = from.coord.lon
        )

        val main = LocalCurrentWeatherMain(
            feelsLike = from.main.feelsLike,
            humidity = from.main.humidity,
            pressure = from.main.pressure,
            temp = from.main.temp,
            tempMax = from.main.tempMax,
            tempMin = from.main.tempMin
        )

        val sys = LocalCurrentWeatherSys(
            country = from.sys.country,
            id = from.sys.id,
            sunrise = from.sys.sunrise,
            sunset = from.sys.sunset,
            type = from.sys.type
        )

        val remoteWeather = from.weather.firstOrNull()
        val weather = LocalCurrentWeatherWeather(
            description = remoteWeather?.description ?: "",
            icon = remoteWeather?.icon ?: "01d",
            id = remoteWeather?.id ?: UInt.MIN_VALUE.toInt(),
            main = remoteWeather?.main ?: ""
        )

        val wind = LocalCurrentWeatherWind(
            deg = from.wind.deg,
            speed = from.wind.speed
        )

        return LocalCurrentWeather(
            base = from.base,
            cod = from.cod,
            dt = from.dt,
            id = from.id,
            name = from.name,
            timezone = from.timezone,
            visibility = from.visibility,
            updatedAt = dateRepo.mapDateTime(dateRepo.nowDateTime()),
            clouds = clouds,
            coord = coord,
            main = main,
            sys = sys,
            weather = weather,
            wind = wind
        )
    }

    companion object {
        private const val TAG = "LocalWeatherMapperImpl"
    }
}
