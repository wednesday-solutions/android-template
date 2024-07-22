package com.wednesday.template.domain.weather

import com.wednesday.template.repo.weather.WeatherRepository
import timber.log.Timber

class FetchFavouriteCitiesWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository,
) : FetchFavouriteCitiesWeatherUseCase {

    override suspend fun invokeInternal(param: Unit) {
        Timber.tag(TAG).d("invokeInternal() called with: param = $param")
        weatherRepository.fetchWeatherForFavouriteCities()
    }

    companion object {
        private const val TAG = "FetchFavouriteCitiesWeatherUseCaseImpl"
    }
}
