package com.wednesday.template.interactor.weather.favourite

import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.interactor.base.Mapper
import com.wednesday.template.interactor_impl.R
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.presentation.weather.UIWeather
import timber.log.Timber
import java.util.*

interface UIWeatherListMapper : Mapper<List<Weather>, UIList>

class UIWeatherListMapperImpl : UIWeatherListMapper {

    override fun map(from: List<Weather>): UIList {
        Timber.tag(TAG).d("map() called with: from = $from")
        val weatherList = from
            .sortedBy { it.title }
            .map {
                UIWeather(
                    lat = it.lat,
                    lon = it.lon,
                    title = UIText { block(it.title) },
                    description = UIText {
                        block(it.description.replaceFirstChar { char ->
                            if (char.isLowerCase()) char.titlecase(
                                Locale.getDefault()
                            ) else char.toString()
                        })
                    },
                    currentTemp = UIText { block("${it.temp} °C") },
                    minMaxTemp = UIText { block("With a high of ${it.maxTemp} °C and low of ${it.minTemp} °C") },
                    feelsLike = UIText {
                        block(R.string.feels_like)
                        block(" ${it.feelsLike} °C")
                    },
                    iconUrl = it.iconUrl
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
