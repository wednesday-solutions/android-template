package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.repo.util.mapper.Mapper
import com.wednesday.template.service.weather.LocalCity
import com.wednesday.template.service.weather.RemoteCity
import timber.log.Timber

interface DomainCityMapper : Mapper<LocalCity, City> {
    fun mapRemoteCity(from: RemoteCity): City
    fun mapRemoteCity(from: List<RemoteCity>): List<City> = from.map(::mapRemoteCity)
}

class DomainCityMapperImpl : DomainCityMapper {

    override fun mapRemoteCity(from: RemoteCity): City {
        Timber.tag(TAG).d("mapRemoteCity: from = $from")
        return City(
            id = from.woeid,
            title = from.title,
            locationType = from.locationType
        )
    }

    override fun map(from: LocalCity): City {
        Timber.tag(TAG).d("map: from = $from")
        return City(
            id = from.woeid,
            title = from.title,
            locationType = from.locationType
        )
    }

    companion object {
        private const val TAG = "DomainCityMapperImpl"
    }

}