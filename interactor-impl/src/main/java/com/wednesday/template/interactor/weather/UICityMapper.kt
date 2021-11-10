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
            title = UIText(from.title),
            locationType = UIText(from.locationType)
        )
    }

    override fun mapUICity(from: UICity): City {
        Timber.tag(TAG).d("mapUICity: from = $from")
        return City(
            id = from.cityId,
            title = from.title.text,
            locationType = from.title.text
        )
    }

    companion object {
        private const val TAG = "UICityMapperImpl"
    }
}
