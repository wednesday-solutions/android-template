package com.wednesday.template.service.weather

import com.wednesday.template.service.openWeather.geoCoding.RemoteLocation
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherRemoteService {

    @GET("geo/1.0/direct")
    suspend fun geocodingSearch(@Query("q") searchTerm: String): List<RemoteLocation>
}