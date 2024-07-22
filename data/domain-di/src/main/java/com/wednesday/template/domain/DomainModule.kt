package com.wednesday.template.domain

import com.wednesday.template.domain.base.datetime.ConvertDateToLongUseCase
import com.wednesday.template.domain.base.datetime.ConvertDateToLongUseCaseImpl
import com.wednesday.template.domain.base.datetime.FormatDateUseCase
import com.wednesday.template.domain.base.datetime.FormatDateUseCaseImpl
import com.wednesday.template.domain.weather.FetchFavouriteCitiesWeatherUseCase
import com.wednesday.template.domain.weather.FetchFavouriteCitiesWeatherUseCaseImpl
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCase
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCaseImpl
import com.wednesday.template.domain.weather.GetFavouriteCitiesWeatherFlowUseCase
import com.wednesday.template.domain.weather.GetFavouriteCitiesWeatherFlowUseCaseImpl
import com.wednesday.template.domain.weather.RemoveCityFavouriteUseCase
import com.wednesday.template.domain.weather.RemoveCityFavouriteUseCaseImpl
import com.wednesday.template.domain.weather.SearchCitiesUseCase
import com.wednesday.template.domain.weather.SearchCitiesUseCaseImpl
import com.wednesday.template.domain.weather.SetCityFavouriteUseCase
import com.wednesday.template.domain.weather.SetCityFavouriteUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    // Date Time
    single<ConvertDateToLongUseCase> { ConvertDateToLongUseCaseImpl(get()) }

    single<FormatDateUseCase> { FormatDateUseCaseImpl(get()) }

    // Weather
    single<GetFavouriteCitiesFlowUseCase> { GetFavouriteCitiesFlowUseCaseImpl(get()) }

    single<SetCityFavouriteUseCase> { SetCityFavouriteUseCaseImpl(get()) }

    single<RemoveCityFavouriteUseCase> { RemoveCityFavouriteUseCaseImpl(get()) }

    single<SearchCitiesUseCase> { SearchCitiesUseCaseImpl(get()) }

    single<GetFavouriteCitiesWeatherFlowUseCase> { GetFavouriteCitiesWeatherFlowUseCaseImpl(get()) }

    single<FetchFavouriteCitiesWeatherUseCase> { FetchFavouriteCitiesWeatherUseCaseImpl(get()) }
}
