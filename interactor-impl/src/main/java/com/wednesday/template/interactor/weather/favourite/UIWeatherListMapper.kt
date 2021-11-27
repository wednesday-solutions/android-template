package com.wednesday.template.interactor.weather.favourite

import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.interactor.base.Mapper
import com.wednesday.template.interactor_impl.R
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.presentation.weather.UIWeather
import timber.log.Timber

interface UIWeatherListMapper : Mapper<List<Weather>, UIList>

class UIWeatherListMapperImpl : UIWeatherListMapper {

    override fun map(from: List<Weather>): UIList {
        Timber.tag(TAG).d("map() called with: from = $from")
        val weatherList = from.map {
            UIWeather(
                cityId = it.woeid,
                title = UIText { block(it.title) },
                currentTemp = UIText { block("${it.temp.toInt()} °C") },
                minMaxTemp = UIText { block("${it.minTemp.toInt()} - ${it.maxTemp.toInt()} °C") }
            )
        }

        if (weatherList.isEmpty()) {
            return UIList(UISearchCitiesPlaceholder(
                text = UIText { block(R.string.search_cities_to_add_to_fav) }
            ))
        }

        return UIList(weatherList)
    }

    companion object {
        private const val TAG = "UIWeatherListMapperImpl"
    }
}