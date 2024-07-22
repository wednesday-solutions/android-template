package com.wednesday.template.service

import com.wednesday.template.service.base.getRoomDatabase
import com.wednesday.template.service.base.retrofit.getOpenWeatherRetrofit
import com.wednesday.template.service.base.retrofit.interceptors.OpenWeatherApiKeyInterceptor
import com.wednesday.template.service.openWeather.OpenWeatherLocalServiceImpl
import com.wednesday.template.service.room.AndroidTemplateDatabase
import com.wednesday.template.service.weather.OpenWeatherLocalService
import com.wednesday.template.service.weather.OpenWeatherRemoteService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    // Retrofit
    factory { OpenWeatherApiKeyInterceptor() }

    single { getOpenWeatherRetrofit(get(), get<OpenWeatherApiKeyInterceptor>()) }

    // Room
    single { getRoomDatabase(get()) }

    // OpenWeather
    single { getWeatherRemoteService(get()) }

    single<OpenWeatherLocalService> { getWeatherLocalService(get()) }
}

fun getWeatherLocalService(database: AndroidTemplateDatabase): OpenWeatherLocalServiceImpl {
    return database.databaseDao()
}

fun getWeatherRemoteService(retrofit: Retrofit): OpenWeatherRemoteService {
    return retrofit.create(OpenWeatherRemoteService::class.java)
}
