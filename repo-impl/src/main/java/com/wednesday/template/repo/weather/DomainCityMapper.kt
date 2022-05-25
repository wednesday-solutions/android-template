package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.repo.util.Mapper
import com.wednesday.template.service.openWeather.geoCoding.LocalLocation
import com.wednesday.template.service.openWeather.geoCoding.RemoteLocation
import timber.log.Timber

interface DomainCityMapper : Mapper<LocalLocation, City> {
    fun mapRemoteCity(from: RemoteLocation): City
    fun mapRemoteCity(from: List<RemoteLocation>): List<City> = from.map(::mapRemoteCity)
}

class DomainCityMapperImpl : DomainCityMapper {

    override fun mapRemoteCity(from: RemoteLocation): City {
        Timber.tag(TAG).d("mapRemoteCity: from = $from")
        return City(
            id = (from.lat + from.lon).toInt(),
            title = from.name,
            country = from.country,
            lat = from.lat,
            lon = from.lon,
            state = from.state
        )
    }

    override fun map(from: LocalLocation): City {
        Timber.tag(TAG).d("map: from = $from")
        return City(
            id = (from.lat + from.lon).toInt(),
            title = from.name,
            country = from.country,
            lat = from.lat,
            lon = from.lon,
            state = from.state
        )
    }

    companion object {
        private const val TAG = "DomainCityMapperImpl"
    }
}
