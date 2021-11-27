package com.wednesday.template.repo.weather

import com.wednesday.template.domain.date.JavaDate
import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.util.Mapper2
import com.wednesday.template.service.weather.LocalWeather
import com.wednesday.template.service.weather.RemoteWeather
import timber.log.Timber

interface LocalWeatherMapper : Mapper2<RemoteWeather, Int, LocalWeather>

class LocalWeatherMapperImpl(
    private val dateRepo: DateRepo
) : LocalWeatherMapper {

    override fun map(from1: RemoteWeather, from2: Int): LocalWeather {
        Timber.tag(TAG).d("map() called with: from1 = $from1, from2 = $from2")
        val todayDate = dateRepo.todayDate()
        val weatherForToday = from1.consolidatedWeathers.firstOrNull {
            it.date.toDate() == todayDate
        } ?: from1.consolidatedWeathers.first()

        return LocalWeather(
            cityWoeid = from2,
            title = from1.title,
            temp = weatherForToday.theTemp,
            minTemp = weatherForToday.minTemp,
            maxTemp = weatherForToday.maxTemp,
            date = weatherForToday.date
        )
    }

    private fun JavaDate.toDate() = dateRepo.mapDate(this)

    companion object {
        private const val TAG = "LocalWeatherMapperImpl"
    }
}
