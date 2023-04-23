package com.wednesday.template.service.weather

import com.wednesday.template.service.openWeather.currentWeather.remote.RemoteCurrentWeather
import com.wednesday.template.service.openWeather.geoCoding.RemoteLocation
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherRemoteService {

    @GET("geo/1.0/direct")
    suspend fun geocodingSearch(
        @Query("q") searchTerm: String,
        @Query("limit") limit: Int = 5,
    ): List<RemoteLocation>

    @GET("data/2.5/weather?units=metric")
    suspend fun currentWeather(
        @Query("q") cityAndState: String,
    ): RemoteCurrentWeather
}
