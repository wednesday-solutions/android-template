package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.repo.util.Mapper
import com.wednesday.template.service.openWeather.geoCoding.LocalLocation
import timber.log.Timber

interface LocalCityMapper : Mapper<City, LocalLocation>

class LocalCityMapperImpl : LocalCityMapper {

    override fun map(from: City): LocalLocation {
        Timber.tag(TAG).d("map: from = $from")
        val (lat, lon) = from.latitude.split(" ")
        return LocalLocation(
            country = from.country,
            name = from.title,
            lat = lat.toDouble(),
            lon = lon.toDouble(),
        )
    }

    companion object {
        private const val TAG = "LocalCityMapperImpl"
    }
}
