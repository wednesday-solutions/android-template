package com.wednesday.template.service.weather

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherRemoteService {

    @GET("/api/location/search")
    suspend fun searchCities(@Query("query") city: String): List<RemoteCity>

    @GET("/api/location/{id}")
    suspend fun weatherForCity(@Path("id") id: Int): RemoteWeather
}
