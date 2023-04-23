package com.wednesday.template.domain.weather

import com.wednesday.template.repo.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class GetFavouriteCitiesWeatherFlowUseCaseImpl(
    private val weatherRepository: WeatherRepository,
) : GetFavouriteCitiesWeatherFlowUseCase {

    override fun invokeInternal(param: Unit): Flow<List<Weather>> {
        Timber.tag(TAG).d("invokeInternal() called with: param = $param")
        return weatherRepository.getFavouriteCitiesWeatherFlow()
            .onEach { Timber.tag(TAG).d("invokeInternal: emit = $it") }
    }

    companion object {
        private const val TAG = "GetFavouriteCitiesWeatherFlowUseCaseImpl"
    }
}
