package com.wednesday.template.domain.weather

import com.wednesday.template.repo.weather.WeatherRepository
import timber.log.Timber

class RemoveCityFavouriteUseCaseImpl(
    private val weatherRepository: WeatherRepository,
) : RemoveCityFavouriteUseCase {

    override suspend fun invokeInternal(param: City) {
        Timber.tag(TAG).d("invokeInternal: param = $param")
        return weatherRepository.removeCityAsFavourite(param)
    }

    companion object {
        private const val TAG = "RemoveCityFavouriteUseCaseImpl"
    }
}
