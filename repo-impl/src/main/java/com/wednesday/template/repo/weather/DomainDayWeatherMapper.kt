package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.DayWeather
import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.util.Mapper
import com.wednesday.template.service.weather.LocalDayWeather
import timber.log.Timber

interface DomainDayWeatherMapper : Mapper<LocalDayWeather, DayWeather>

class DomainDayWeatherMapperImpl(
    private val dateRepo: DateRepo
) : DomainDayWeatherMapper {

    override fun map(from: LocalDayWeather): DayWeather {
        Timber.tag(TAG).d("map: from = $from")

        val date = dateRepo.mapDate(from.date)

        return DayWeather(
            minTemp = from.minTemp,
            maxTemp = from.maxTemp,
            temp = from.temp,
            date = date,
            isToday = date == dateRepo.todayDate()
        )
    }

    companion object {
        private const val TAG = "DomainDayWeatherMapperImpl"
    }
}
