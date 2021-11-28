package com.wednesday.template.repo

import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.date.DateRepoImpl
import com.wednesday.template.repo.weather.*
import org.koin.dsl.module

val repoModule = module {

    // Date Time
    single<DateRepo> { DateRepoImpl() }

    // Weather
    single<DomainCityMapper> { DomainCityMapperImpl() }

    single<LocalCityMapper> { LocalCityMapperImpl() }

    single<DomainWeatherMapper> { DomainWeatherMapperImpl(get()) }

    single<LocalWeatherMapper> { LocalWeatherMapperImpl() }

    single<LocalDayWeatherMapper> { LocalDayWeatherMapperImpl() }

    single<DomainDayWeatherMapper> { DomainDayWeatherMapperImpl(get()) }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            get(), get(), get(), get(), get(), get(),
            get(), get()
        )
    }
}
