package com.wednesday.template.service_di

import com.wednesday.template.service_impl.base.getRetrofit
import com.wednesday.template.service_impl.base.getRoomDatabase
import com.wednesday.template.service_impl.room.AndroidTemplateDatabase
import com.wednesday.template.service_impl.weather.WeatherLocalService
import com.wednesday.template.service_impl.weather.WeatherRemoteService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    // Retrofit
    single { getRetrofit() }

    // Room
    single { getRoomDatabase(get()) }

    // Weather
    single { getWeatherRemoteService(get()) }
    single { getWeatherLocalService(get()) }
}

fun getWeatherLocalService(database: AndroidTemplateDatabase): WeatherLocalService {
    return database.databaseDao()
}

fun getWeatherRemoteService(retrofit: Retrofit): WeatherRemoteService {
    return retrofit.create(WeatherRemoteService::class.java)
}
