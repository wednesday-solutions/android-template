package com.wednesday.template.network

import com.wednesday.template.model.City
import com.wednesday.template.model.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/api/location/search")
    suspend fun searchCities(@Query("query") city: String): List<City>

    @GET("/api/location/{id}")
    suspend fun weatherForCity(@Path("id") id: Int): Weather
}
