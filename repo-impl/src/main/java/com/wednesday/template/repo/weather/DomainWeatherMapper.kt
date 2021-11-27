package com.wednesday.template.repo.weather

import com.wednesday.template.domain.date.JavaDate
import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.util.Mapper
import com.wednesday.template.service.weather.LocalCityWithWeather
import timber.log.Timber

interface DomainWeatherMapper : Mapper<LocalCityWithWeather, Weather>

class DomainWeatherMapperImpl(
    private val dateRepo: DateRepo
) : DomainWeatherMapper {

    override fun map(from: LocalCityWithWeather): Weather {
        Timber.tag(TAG).d("map() called with: from = $from")
        return Weather(
            title = from.weather.title,
            woeid = from.city.woeid,
            minTemp = from.weather.minTemp,
            maxTemp = from.weather.maxTemp,
            temp = from.weather.temp,
            date = from.weather.date.toDate()
        )
    }

    private fun JavaDate.toDate() = dateRepo.mapDate(this)

    companion object {
        private const val TAG = "DomainWeatherMapperImpl"
    }
}
