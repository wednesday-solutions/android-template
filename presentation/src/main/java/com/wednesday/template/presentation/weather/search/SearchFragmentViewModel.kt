package com.wednesday.template.presentation.weather.search

import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val searchCityInteractor: SearchCityInteractor
) : BaseViewModel<SearchFragmentScreen, SearchFragmentScreenState>(),
    IntentHandler<SearchScreenIntent> {

    private val mutableStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override fun getDefaultScreenState(): SearchFragmentScreenState {
        return SearchFragmentScreenState(UIToolbar(UIText(), true, UIText()), false, UIList())
    }

    @FlowPreview
    override fun onCreate(fromRecreate: Boolean) {
            mutableStateFlow
                .debounce(500)
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .onEach {
                    val result = searchCityInteractor.search(it)
                    setState {
                        copy(showLoading = false, searchList = result)
                    }
                }
                .launchIn(viewModelScope)
    }

    override fun onIntent(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.SearchCities -> {
                viewModelScope.launch {
                    setState {
                        copy(showLoading = true)
                    }
                    mutableStateFlow.value = intent.city
                }
            }
        }
    }
}
