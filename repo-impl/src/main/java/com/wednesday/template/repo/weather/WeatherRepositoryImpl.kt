package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.service.WeatherLocalService
import com.wednesday.template.service.weather.WeatherLocalServiceImpl
import com.wednesday.template.service.weather.WeatherRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber


class WeatherRepositoryImpl(
    private val weatherRemoteService: WeatherRemoteService,
    private val weatherLocalService: WeatherLocalService,
    private val domainCityMapper: DomainCityMapper,
    private val localCityMapper: LocalCityMapper
) : WeatherRepository {

    override suspend fun searchCities(searchTerm: String): List<City> {
        Timber.tag(TAG).d("searchCities: searchTerm = $searchTerm")
        return weatherRemoteService.searchCities(searchTerm)
            .let { domainCityMapper.mapRemoteCity(it) }
    }

    override fun getFavouriteCitiesFlow(): Flow<List<City>> {
        Timber.tag(TAG).d("getFavouriteCitiesFlow")
        return weatherLocalService
            .getFavoriteCitiesFlow()
            .map { domainCityMapper.map(it) }
            .onEach { Timber.tag(TAG).d("getFavouriteCitiesFlow: emit = $it") }
    }

    override suspend fun setCityAsFavourite(city: City) {
        Timber.tag(TAG).d("setCityAsFavourite: city = $city")
        weatherLocalService.markCityAsFavorite(localCityMapper.map(city))
    }

    override suspend fun removeCityAsFavourite(city: City) {
        Timber.tag(TAG).d("removeCityAsFavourite: city = $city")
        weatherLocalService.deleteFavoriteCity(localCityMapper.map(city))
    }

    companion object {
        private const val TAG = "WeatherRepositoryImpl"
    }
}