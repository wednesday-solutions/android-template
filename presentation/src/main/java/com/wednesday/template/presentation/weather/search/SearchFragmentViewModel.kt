package com.wednesday.template.presentation.weather.search

import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val searchCityInteractor: SearchCityInteractor
) : BaseViewModel<SearchFragmentScreen, SearchFragmentScreenState>(),
    IntentHandler<SearchScreenIntent> {

    override fun getDefaultScreenState(): SearchFragmentScreenState {
        return SearchFragmentScreenState(UIToolbar(UIText(), true, UIText()), false, UIList())
    }

    override fun onCreate(fromRecreate: Boolean) {
    }

    override fun onIntent(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.SearchCities -> {
                viewModelScope.launch {
                    setState {
                        copy(showLoading = true)
                    }
                    val result = searchCityInteractor.search(intent.city)
                    setState {
                        copy(showLoading = false, searchList = result)
                    }
                }
            }
        }
    }
}
