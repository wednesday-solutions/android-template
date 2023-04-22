package com.wednesday.template.interactor.weather.search

import com.wednesday.template.data.core.Mapper2
import com.wednesday.template.domain.weather.City
import com.wednesday.template.interactor.weather.UICityMapper
import com.wednesday.template.presentation.base.UIList
import timber.log.Timber

interface UICitySearchResultsMapper : Mapper2<List<City>, List<City>, UIList>

class UICitySearchResultsMapperImpl(
    private val cityMapper: UICityMapper
) : UICitySearchResultsMapper {

    override fun map(from1: List<City>, from2: List<City>): UIList {
        Timber.tag(TAG).d("map: from1 = $from1, from2 = $from2")
        val uiCityList = from2.map {
            val isFavourite = from1.contains(it)
            cityMapper.map(it, isFavourite)
        }
        return UIList(uiCityList)
    }

    companion object {
        private const val TAG = "UICitySearchResultsMapperImpl"
    }
}