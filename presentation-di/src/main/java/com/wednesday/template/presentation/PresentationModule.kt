package com.wednesday.template.presentation

import androidx.fragment.app.Fragment
import com.wednesday.template.presentation.weather.search.SearchFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val presentationModule = module {
    viewModel { (fragment: Fragment) ->
        SearchFragmentViewModel(get(), get(), get { parametersOf(fragment) })
    }
}
