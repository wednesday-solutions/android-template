package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.domain.weather.Weather
import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.service.weather.OpenWeatherLocalService
import com.wednesday.template.service.weather.OpenWeatherRemoteService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class WeatherRepositoryImpl(
    private val weatherRemoteService: OpenWeatherRemoteService,
    private val weatherLocalService: OpenWeatherLocalService,
    private val domainCityMapper: DomainCityMapper,
    private val localCityMapper: LocalCityMapper,
    private val localWeatherMapper: LocalWeatherMapper,
    private val localDayWeatherMapper: LocalDayWeatherMapper,
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
//        val todayDate = dateRepo.todayDate()
//        weatherLocalService.getFavoriteCities().map {
//            async {
//                val dayWeatherList = weatherLocalService.getLocalDayWeather(woeid = it.woeid)
//                val isWeatherListStale =
//                    dayWeatherList.find { dateRepo.mapDate(it.date) == todayDate } == null
//
//                if (dayWeatherList.isEmpty() || isWeatherListStale) {
//                    val remoteWeather = weatherRemoteService.weatherForCity(it.woeid)
//
//                    weatherLocalService.deleteCurrentAndAddNewWeatherData(
//                        woeid = it.woeid,
//                        weather = localWeatherMapper.map(remoteWeather, it.woeid),
//                        weatherList = localDayWeatherMapper.map(remoteWeather, it.woeid)
//                    )
//                }
//            }
//        }.awaitAll()
    }

    override suspend fun getFavouriteCitiesWeatherList(): List<Weather> {
        Timber.tag(TAG).d("getFavouriteCitiesWeatherList() called")
//        return weatherLocalService
//            .getFavouriteCitiesWeatherList()
//            .let(domainWeatherMapper::map)
        return listOf()
    }

    override fun getFavouriteCitiesWeatherFlow(): Flow<List<Weather>> {
        Timber.tag(TAG).d("getFavouriteCitiesWeatherFlow() called")
//        return weatherLocalService
//            .getFavouriteCitiesWeatherFlow()
//            .map { domainWeatherMapper.map(it) }
//            .onEach { Timber.tag(TAG).d("getFavouriteCitiesWeatherFlow: emit = $it") }
        return flowOf()
    }

    companion object {
        private const val TAG = "WeatherRepositoryImpl"
    }
}
