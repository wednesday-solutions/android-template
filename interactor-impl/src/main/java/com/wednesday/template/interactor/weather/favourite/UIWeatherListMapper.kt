package com.wednesday.template.interactor.weather.favourite

import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.interactor.base.Mapper
import com.wednesday.template.interactor.base.datetime.UIDateMapper
import com.wednesday.template.interactor_impl.R
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.weather.UIDayWeather
import com.wednesday.template.presentation.weather.UIDayWeatherHeading
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.presentation.weather.UIWeather
import timber.log.Timber
import kotlin.math.min

interface UIWeatherListMapper : Mapper<List<Weather>, UIList>

class UIWeatherListMapperImpl(
    private val dayWeatherMapper: UIDayWeatherMapper,
    private val uiDateMapper: UIDateMapper
) : UIWeatherListMapper {

    override fun map(from: List<Weather>): UIList {
        Timber.tag(TAG).d("map() called with: from = $from")
        val weatherList = from
            .sortedBy { it.title }
            .map {

            val currentWeather = it.dayWeatherList.firstOrNull { dayWeather -> dayWeather.isToday }
                ?: it.dayWeatherList.first()

            val dayWeatherList = mutableListOf<UIListItemBase>()

            dayWeatherList.add(
                UIDayWeatherHeading(
                    text = UIText { block(R.string.forecast) }
                )
            )

            dayWeatherList.addAll(
                it.dayWeatherList
                    .filter { dayWeather -> !dayWeather.isToday }
                    .sortedBy { dayWeather -> uiDateMapper.map(dayWeather.date).timeAsLong }
                    .map { dayWeather -> dayWeatherMapper.map(dayWeather, it.woeid) }
            )

            UIWeather(
                cityId = it.woeid,
                title = UIText { block(it.title) },
                currentTemp = UIText { block("${currentWeather.temp} °C") },
                minMaxTemp = UIText { block("${currentWeather.minTemp} - ${currentWeather.maxTemp} °C") },
                dayWeatherList = dayWeatherList
            )
        }

        if (weatherList.isEmpty()) {
            return UIList(
                UISearchCitiesPlaceholder(
                    text = UIText { block(R.string.search_cities_to_add_to_fav) }
                )
            )
        }

        return UIList(weatherList)
    }

    companion object {
        private const val TAG = "UIWeatherListMapperImpl"
    }
}
