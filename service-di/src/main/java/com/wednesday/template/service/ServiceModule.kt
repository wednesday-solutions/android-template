package com.wednesday.template.service

import com.wednesday.template.service.base.getOpenWeatherRetrofit
import com.wednesday.template.service.base.getRoomDatabase
import com.wednesday.template.service.openWeather.OpenWeatherLocalServiceImpl
import com.wednesday.template.service.room.AndroidTemplateDatabase
import com.wednesday.template.service.weather.OpenWeatherLocalService
import com.wednesday.template.service.weather.OpenWeatherRemoteService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    // Retrofit
    single { getOpenWeatherRetrofit(get()) }

    // Room
    single { getRoomDatabase(get()) }

    // Weather
    single { getWeatherRemoteService(get()) }
    single<OpenWeatherLocalService> { getWeatherLocalService(get()) }

    // OpenWeather
}

fun getWeatherLocalService(database: AndroidTemplateDatabase): OpenWeatherLocalServiceImpl {
    return database.databaseDao()
}

fun getWeatherRemoteService(retrofit: Retrofit): OpenWeatherRemoteService {
    return retrofit.create(OpenWeatherRemoteService::class.java)
}
