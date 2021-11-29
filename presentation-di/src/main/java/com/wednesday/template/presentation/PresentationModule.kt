package com.wednesday.template.presentation

import com.wednesday.template.presentation.weather.home.HomeViewModel
import com.wednesday.template.presentation.weather.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SearchViewModel(get(), get()) }

    viewModel { HomeViewModel(get()) }
}
