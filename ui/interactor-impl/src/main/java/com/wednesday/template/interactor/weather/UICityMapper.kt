package com.wednesday.template.interactor.weather

import com.wednesday.template.data.core.Mapper2
import com.wednesday.template.domain.weather.City
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.weather.UICity
import timber.log.Timber

interface UICityMapper : Mapper2<City, Boolean, UICity> {

    fun mapUICity(from: UICity): City

    fun mapFavouriteCity(from: City): UICity

    fun mapFavouriteCity(from: List<City>): List<UICity> = from.map(::mapFavouriteCity)
}

class UICityMapperImpl : UICityMapper {

    override fun map(from1: City, from2: Boolean): UICity {
        Timber.tag(TAG).d("map: from1 = $from1, from2 = $from2")
        return UICity(
            cityId = from1.id,
            title = from1.title,
            locationType = from1.country,
            displayTitle = UIText { block(from1.title) },
            displayLocationType = UIText { block(from1.country) },
            latitude = "${from1.lat} ${from1.lon}",
            isFavourite = from2,
            state = from1.state
        )
    }

    override fun mapUICity(from: UICity): City {
        Timber.tag(TAG).d("mapUICity: from = $from")
        val (lat, lon) = from.latitude.split(" ")
        return City(
            id = from.cityId,
            title = from.title,
            country = from.locationType,
            lat = lat.toDouble(),
            lon = lon.toDouble(),
            state = from.state
        )
    }

    override fun mapFavouriteCity(from: City): UICity {
        Timber.tag(TAG).d("mapFavouriteCity() called with: from = $from")
        return UICity(
            cityId = from.id,
            title = from.title,
            locationType = from.country,
            displayTitle = UIText { block(from.title) },
            displayLocationType = UIText { block(from.country) },
            latitude = "${from.lat} ${from.lon}",
            isFavourite = true,
            state = from.state
        )
    }

    companion object {
        private const val TAG = "UICityMapperImpl"
    }
}
