package com.wednesday.template.domain.weather

import com.wednesday.template.repo.weather.WeatherRepository
import timber.log.Timber

class SetCityFavouriteUseCaseImpl(
    private val weatherRepository: WeatherRepository,
) : SetCityFavouriteUseCase {

    override suspend fun invokeInternal(param: City) {
        Timber.tag(TAG).d("invokeInternal: param = $param")
        return weatherRepository.setCityAsFavourite(param)
    }

    companion object {
        private const val TAG = "SetCityFavouriteUseCaseImpl"
    }
}
