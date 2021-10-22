package com.wednesday.template.domain.weather

import com.wednesday.template.repo.weather.WeatherRepository
import timber.log.Timber

class SearchCitiesUseCaseImpl(
    private val weatherRepository: WeatherRepository
): SearchCitiesUseCase {

    override suspend fun invokeInternal(param: String): List<City> {
       Timber.tag(TAG).d("invokeInternal: param = $param")
        return weatherRepository.searchCities(param)
    }

    companion object {
        private const val TAG = "SearchCitiesUseCaseImpl"
    }
}