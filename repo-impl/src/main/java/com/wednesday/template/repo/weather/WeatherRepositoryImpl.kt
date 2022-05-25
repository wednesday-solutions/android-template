package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.service.weather.OpenWeatherLocalService
import com.wednesday.template.service.weather.OpenWeatherRemoteService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class WeatherRepositoryImpl(
    private val weatherRemoteService: OpenWeatherRemoteService,
    private val weatherLocalService: OpenWeatherLocalService,
    private val domainCityMapper: DomainCityMapper,
    private val localCityMapper: LocalCityMapper,
    private val localWeatherMapper: LocalWeatherMapper,
    private val domainWeatherMapper: DomainWeatherMapper,
    private val dateRepo: DateRepo
) : WeatherRepository {

    override suspend fun searchCities(searchTerm: String): List<City> {
        Timber.tag(TAG).d("searchCities: searchTerm = $searchTerm")
        return weatherRemoteService.geocodingSearch(searchTerm)
            .let { domainCityMapper.mapRemoteCity(it) }
    }

    override fun getFavouriteCitiesFlow(): Flow<List<City>> {
        Timber.tag(TAG).d("getFavouriteCitiesFlow")
        return weatherLocalService
            .getFavoriteCitiesFlow()
            .map { domainCityMapper.map(it) }
            .onEach { Timber.tag(TAG).d("getFavouriteCitiesFlow: emit = $it") }
    }

    override suspend fun getFavouriteCitiesList(): List<City> {
        Timber.tag(TAG).d("getFavouriteCitiesList() called")
        return weatherLocalService.getFavoriteCities().let(domainCityMapper::map)
    }

    override suspend fun setCityAsFavourite(city: City) {
        Timber.tag(TAG).d("setCityAsFavourite: city = $city")
        weatherLocalService.markCityAsFavorite(localCityMapper.map(city))
    }

    override suspend fun removeCityAsFavourite(city: City) {
        Timber.tag(TAG).d("removeCityAsFavourite: city = $city")
        weatherLocalService.deleteFavoriteCity(localCityMapper.map(city))
    }

    override suspend fun fetchWeatherForFavouriteCities(): Unit = coroutineScope {
        Timber.tag(TAG).d("fetchWeatherForFavouriteCities() called")
        val nowMillis = dateRepo.nowDateTimeAsLong()
        val twoHours = 2 * 60 * 60 * 1000
        weatherLocalService.getFavoriteCities().map {
            async {
                val localCurrentWeather =
                    weatherLocalService.getLocalCurrentWeather(lat = it.lat, lon = it.lon)
                val isWeatherDataStale =
                    localCurrentWeather != null && (nowMillis - localCurrentWeather.updatedAt.time) > twoHours

                if (localCurrentWeather == null || isWeatherDataStale) {
                    val remoteCurrentWeather =
                        weatherRemoteService.currentWeather(lat = it.lat, lon = it.lon)

                    weatherLocalService.upsertLocalCurrentWeather(
                        weather = localWeatherMapper.map(remoteCurrentWeather)
                    )
                }
            }
        }.awaitAll()
    }

    override suspend fun getFavouriteCitiesWeatherList(): List<Weather> {
        Timber.tag(TAG).d("getFavouriteCitiesWeatherList() called")
        return weatherLocalService
            .getFavouriteCitiesWeatherList()
            .let(domainWeatherMapper::map)
    }

    override fun getFavouriteCitiesWeatherFlow(): Flow<List<Weather>> {
        Timber.tag(TAG).d("getFavouriteCitiesWeatherFlow() called")
        return weatherLocalService
            .getFavouriteCitiesWeatherFlow()
            .map { domainWeatherMapper.map(it) }
            .onEach { Timber.tag(TAG).d("getFavouriteCitiesWeatherFlow: emit = $it") }
    }

    companion object {
        private const val TAG = "WeatherRepositoryImpl"
    }
}
