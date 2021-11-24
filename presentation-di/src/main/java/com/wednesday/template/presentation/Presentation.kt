package com.wednesday.template.presentation

import com.wednesday.template.presentation.weather.search.SearchFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SearchFragmentViewModel(get(),get())
    }
}
