package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.repo.util.Mapper
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeather
import timber.log.Timber

interface DomainWeatherMapper : Mapper<LocalCurrentWeather, Weather>

class DomainWeatherMapperImpl : DomainWeatherMapper {

    override fun map(from: LocalCurrentWeather): Weather {
        Timber.tag(TAG).d("map() called with: from = $from")

        return Weather(
            title = "${from.name}, ${from.sys.country}",
            description = from.weather.description,
            lat = from.coord.lat,
            lon = from.coord.lon,
            minTemp = from.main.tempMin,
            maxTemp = from.main.tempMax,
            temp = from.main.temp,
            feelsLike = from.main.feelsLike,
            iconUrl = "https://openweathermap.org/img/wn/${from.weather.icon}@2x.png"
        )
    }

    companion object {
        private const val TAG = "DomainWeatherMapperImpl"
    }
}
