package com.wednesday.template.repo

import com.wednesday.template.repo.weather.DomainCityMapper
import com.wednesday.template.repo.weather.DomainCityMapperImpl
import com.wednesday.template.repo.weather.LocalCityMapper
import com.wednesday.template.repo.weather.LocalCityMapperImpl
import com.wednesday.template.repo.weather.WeatherRepository
import com.wednesday.template.repo.weather.WeatherRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    // Weather
    single<DomainCityMapper> { DomainCityMapperImpl() }

    single<LocalCityMapper> { LocalCityMapperImpl() }

    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get(), get()) }
}
