package com.wednesday.template.service

import com.wednesday.template.service.base.getRetrofit
import com.wednesday.template.service.base.getRoomDatabase
import com.wednesday.template.service.room.AndroidTemplateDatabase
import com.wednesday.template.service.weather.WeatherLocalService
import com.wednesday.template.service.weather.WeatherLocalServiceImpl
import com.wednesday.template.service.weather.WeatherRemoteService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    // Retrofit
    single { getRetrofit(get()) }

    // Room
    single { getRoomDatabase(get()) }

    // Weather
    single { getWeatherRemoteService(get()) }
    single<WeatherLocalService> { getWeatherLocalService(get()) }
}

fun getWeatherLocalService(database: AndroidTemplateDatabase): WeatherLocalServiceImpl {
    return database.databaseDao()
}

fun getWeatherRemoteService(retrofit: Retrofit): WeatherRemoteService {
    return retrofit.create(WeatherRemoteService::class.java)
}
