package com.wednesday.template.interactor.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.interactor.base.Mapper
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.weather.UICity
import timber.log.Timber

interface UICityMapper : Mapper<City, UICity> {

    fun mapUICity(from: UICity): City
}

class UICityMapperImpl : UICityMapper {

    override fun map(from: City): UICity {
        Timber.tag(TAG).d("map: from = $from")
        return UICity(
            cityId = from.id,
            title = from.title,
            locationType = from.locationType,
            displayTitle = UIText { block(from.title) },
            displayLocationType = UIText { block(from.locationType) },
            latitude = from.latitude
        )
    }

    override fun mapUICity(from: UICity): City {
        Timber.tag(TAG).d("mapUICity: from = $from")
        return City(
            id = from.cityId,
            title = from.title,
            locationType = from.locationType,
            latitude = from.latitude
        )
    }

    companion object {
        private const val TAG = "UICityMapperImpl"
    }
}
