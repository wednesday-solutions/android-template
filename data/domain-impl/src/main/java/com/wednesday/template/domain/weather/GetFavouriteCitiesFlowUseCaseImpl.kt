package com.wednesday.template.domain.weather

import com.wednesday.template.repo.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class GetFavouriteCitiesFlowUseCaseImpl(
    private val weatherRepository: WeatherRepository,
) : GetFavouriteCitiesFlowUseCase {

    override fun invokeInternal(param: Unit): Flow<List<City>> {
        Timber.tag(TAG).d("invokeInternal")
        return weatherRepository.getFavouriteCitiesFlow()
            .onEach { Timber.tag(TAG).d("invokeInternal: emit = $it") }
    }

    companion object {
        private const val TAG = "GetFavouriteCitiesFlowUseCaseImpl"
    }
}
