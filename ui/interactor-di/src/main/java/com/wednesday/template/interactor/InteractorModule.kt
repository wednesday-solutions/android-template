package com.wednesday.template.interactor

import com.wednesday.template.interactor.base.datetime.UIDateMapper
import com.wednesday.template.interactor.base.datetime.UIDateMapperImpl
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.interactor.weather.UICityMapper
import com.wednesday.template.interactor.weather.UICityMapperImpl
import com.wednesday.template.interactor.weather.favourite.FavouriteWeatherInteractorImpl
import com.wednesday.template.interactor.weather.favourite.UIWeatherListMapper
import com.wednesday.template.interactor.weather.favourite.UIWeatherListMapperImpl
import com.wednesday.template.interactor.weather.search.SearchCityInteractorImpl
import com.wednesday.template.interactor.weather.search.UICitySearchResultsMapper
import com.wednesday.template.interactor.weather.search.UICitySearchResultsMapperImpl
import org.koin.dsl.module

val interactorModule = module {

    // Date Time
    single<UIDateMapper> { UIDateMapperImpl(get()) }

    // Weather
    single<UICityMapper> { UICityMapperImpl() }

    single<UICitySearchResultsMapper> { UICitySearchResultsMapperImpl(get()) }

    single<UIWeatherListMapper> { UIWeatherListMapperImpl() }

    factory<FavouriteWeatherInteractor> {
        FavouriteWeatherInteractorImpl(
            setCityFavouriteUseCase = get(),
            removeCityFavouriteUseCase = get(),
            getFavouriteCitiesFlowUseCase = get(),
            getFavouriteCitiesWeatherFlowUseCase = get(),
            fetchFavouriteCitiesWeatherUseCase = get(),
            uiCityMapper = get(),
            weatherListMapper = get(),
            coroutineContextController = get()
        )
    }

    factory<SearchCityInteractor> {
        SearchCityInteractorImpl(
            searchCitiesUseCase = get(),
            favouriteCitiesFlowUseCase = get(),
            citySearchResultMapper = get(),
            coroutineContextController = get()
        )
    }
}
