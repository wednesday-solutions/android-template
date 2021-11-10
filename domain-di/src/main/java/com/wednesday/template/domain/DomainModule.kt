package com.wednesday.template.domain

import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCase
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCaseImpl
import com.wednesday.template.domain.weather.RemoveCityFavouriteUseCase
import com.wednesday.template.domain.weather.RemoveCityFavouriteUseCaseImpl
import com.wednesday.template.domain.weather.SearchCitiesUseCase
import com.wednesday.template.domain.weather.SearchCitiesUseCaseImpl
import com.wednesday.template.domain.weather.SetCityFavouriteUseCase
import com.wednesday.template.domain.weather.SetCityFavouriteUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    // Weather
    single<GetFavouriteCitiesFlowUseCase> { GetFavouriteCitiesFlowUseCaseImpl(get()) }

    single<SetCityFavouriteUseCase> { SetCityFavouriteUseCaseImpl(get()) }

    single<RemoveCityFavouriteUseCase> { RemoveCityFavouriteUseCaseImpl(get()) }

    single<SearchCitiesUseCase> { SearchCitiesUseCaseImpl(get()) }
}
