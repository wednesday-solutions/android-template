package com.wednesday.template.interactor.weather.favourite

import com.wednesday.template.domain.base.datetime.FormatDateUseCase
import com.wednesday.template.domain.weather.DayWeather
import com.wednesday.template.interactor.base.Mapper2
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.weather.UIDayWeather
import timber.log.Timber

interface UIDayWeatherMapper : Mapper2<DayWeather, Int, UIDayWeather>

class UIDayWeatherMapperImpl(
    private val formatDateUseCase: FormatDateUseCase
) : UIDayWeatherMapper {

    override fun map(from1: DayWeather, from2: Int): UIDayWeather {
        Timber.tag(TAG).d("map: from1 = $from1, from2 = $from2")

        val displayDateFormat = "dd MMM yy"

        return UIDayWeather(
            cityId = from2,
            currentTemp = UIText { block("${from1.temp} °C") },
            minMaxTemp = UIText { block("${from1.minTemp} - ${from1.maxTemp} °C") },
            date = UIText { block(formatDateUseCase(from1.date to displayDateFormat)) }
        )
    }

    companion object {
        private const val TAG = "UIDayWeatherMapperImpl"
    }
}
