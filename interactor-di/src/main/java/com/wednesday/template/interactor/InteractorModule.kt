package com.wednesday.template.interactor

import com.wednesday.template.interactor.base.CoroutineContextController
import com.wednesday.template.interactor.base.CoroutineContextControllerImpl
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.interactor.weather.UICityMapper
import com.wednesday.template.interactor.weather.UICityMapperImpl
import com.wednesday.template.interactor.weather.favourite.FavouriteWeatherInteractorImpl
import com.wednesday.template.interactor.weather.search.SearchCityInteractorImpl
import com.wednesday.template.interactor.weather.search.UICitySearchResultsMapper
import com.wednesday.template.interactor.weather.search.UICitySearchResultsMapperImpl
import org.koin.dsl.module

val interactorModule = module {
    single<CoroutineContextController> { CoroutineContextControllerImpl() }

    // Weather
    single<UICityMapper> { UICityMapperImpl() }

    single<UICitySearchResultsMapper> { UICitySearchResultsMapperImpl(get()) }

    factory<FavouriteWeatherInteractor> { FavouriteWeatherInteractorImpl(get(), get(), get(), get(), get()) }

    factory<SearchCityInteractor> { SearchCityInteractorImpl(get(), get(), get(), get()) }
}
