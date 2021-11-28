package com.wednesday.template.repo.weather

import com.wednesday.template.repo.util.Mapper2
import com.wednesday.template.service.weather.LocalDayWeather
import com.wednesday.template.service.weather.RemoteWeather
import timber.log.Timber

interface LocalDayWeatherMapper: Mapper2<RemoteWeather, Int, List<LocalDayWeather>>

class LocalDayWeatherMapperImpl: LocalDayWeatherMapper {

    override fun map(from1: RemoteWeather, from2: Int): List<LocalDayWeather> {
        Timber.tag(TAG).d("map() called with: from1 = $from1, from2 = $from2")
        return from1.consolidatedWeathers.map { 
            LocalDayWeather(
                cityWoeid = from2,
                temp = it.theTemp.toInt(),
                minTemp = it.minTemp.toInt(),
                maxTemp = it.maxTemp.toInt(),
                date = it.date
            )
        }
    }

    companion object {
        private const val TAG = "LocalDayWeatherMapperImpl"
    }
}