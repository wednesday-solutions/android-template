package com.wednesday.template.home.di

import com.wednesday.template.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            favouriteWeatherInteractor = get(),
        )
    }
}
