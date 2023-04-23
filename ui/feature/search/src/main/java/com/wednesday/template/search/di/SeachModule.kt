package com.wednesday.template.search.di

import com.wednesday.template.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel {
        SearchViewModel(
            searchCityInteractor = get(),
            favouriteWeatherInteractor = get()
        )
    }
}