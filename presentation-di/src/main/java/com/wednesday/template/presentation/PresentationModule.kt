package com.wednesday.template.presentation

import androidx.fragment.app.Fragment
import com.wednesday.template.presentation.weather.home.HomeViewModel
import com.wednesday.template.presentation.weather.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val presentationModule = module {
    viewModel { (fragment: Fragment) ->
        SearchViewModel(get(), get(), get { parametersOf(fragment) })
    }
    viewModel { (fragment: Fragment) ->
        HomeViewModel(get(), get { parametersOf(fragment) })
    }
}
