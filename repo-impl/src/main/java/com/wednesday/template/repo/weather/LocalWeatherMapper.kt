package com.wednesday.template.repo.weather

import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.util.Mapper3
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeather
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherClouds
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherCoord
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherMain
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherSys
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherWeather
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeatherWind
import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeather
import timber.log.Timber

interface LocalWeatherMapper : Mapper3<RemoteCurrentWeather, Double, Double, LocalCurrentWeather>

class LocalWeatherMapperImpl(private val dateRepo: DateRepo) : LocalWeatherMapper {

    override fun map(from1: RemoteCurrentWeather, from2: Double, from3: Double): LocalCurrentWeather {
        Timber.tag(TAG).d("map() called with: from1 = $from1, from2 = $from2, from3 = $from3")

        val clouds = LocalCurrentWeatherClouds(
            all = from1.clouds.all
        )

        val coord = LocalCurrentWeatherCoord(
            lat = from2,
            lon = from3
        )

        val main = LocalCurrentWeatherMain(
            feelsLike = from1.main.feelsLike,
            humidity = from1.main.humidity,
            pressure = from1.main.pressure,
            temp = from1.main.temp,
            tempMax = from1.main.tempMax,
            tempMin = from1.main.tempMin
        )

        val sys = LocalCurrentWeatherSys(
            country = from1.sys.country,
            sunrise = from1.sys.sunrise,
            sunset = from1.sys.sunset,
        )

        val remoteWeather = from1.weather.firstOrNull()
        val weather = LocalCurrentWeatherWeather(
            description = remoteWeather?.description ?: "",
            icon = remoteWeather?.icon ?: "01d",
            id = remoteWeather?.id ?: UInt.MIN_VALUE.toInt(),
            main = remoteWeather?.main ?: ""
        )

        val wind = LocalCurrentWeatherWind(
            deg = from1.wind.deg,
            speed = from1.wind.speed
        )

        return LocalCurrentWeather(
            base = from1.base,
            cod = from1.cod,
            dt = from1.dt,
            id = from1.id,
            name = from1.name,
            timezone = from1.timezone,
            visibility = from1.visibility,
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
