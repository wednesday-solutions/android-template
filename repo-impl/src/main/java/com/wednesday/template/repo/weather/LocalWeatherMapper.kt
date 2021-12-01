package com.wednesday.template.repo.weather

import com.wednesday.template.repo.util.Mapper2
import com.wednesday.template.service.weather.LocalWeather
import com.wednesday.template.service.weather.RemoteWeather
import timber.log.Timber

interface LocalWeatherMapper : Mapper2<RemoteWeather, Int, LocalWeather>

class LocalWeatherMapperImpl : LocalWeatherMapper {

    override fun map(from1: RemoteWeather, from2: Int): LocalWeather {
        Timber.tag(TAG).d("map() called with: from1 = $from1, from2 = $from2")

        return LocalWeather(
            cityWoeid = from2,
            title = from1.title
        )
    }

    companion object {
        private const val TAG = "LocalWeatherMapperImpl"
    }
}
