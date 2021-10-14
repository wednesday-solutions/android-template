package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.repo.util.mapper.Mapper
import com.wednesday.template.service.weather.LocalCity
import timber.log.Timber

interface LocalCityMapper : Mapper<City, LocalCity>

class LocalCityMapperImpl : LocalCityMapper {

    override fun map(from: City): LocalCity {
        Timber.tag(TAG).d("map: from = $from")
        return LocalCity(
            woeid = from.id,
            title = from.title,
            locationType = from.locationType
        )
    }

    companion object {
        private const val TAG = "LocalCityMapperImpl"
    }

}