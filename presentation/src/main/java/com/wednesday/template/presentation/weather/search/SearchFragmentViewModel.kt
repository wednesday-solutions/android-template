package com.wednesday.template.presentation.weather.search

import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.weather.UICity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val searchCityInteractor: SearchCityInteractor,
    private val favouriteWeatherInteractor: FavouriteWeatherInteractor
) : BaseViewModel<SearchFragmentScreen, SearchScreenState>(),
    IntentHandler<SearchScreenIntent> {

    private val searchCityResponseMutableStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override fun getDefaultScreenState(): SearchScreenState {
        return SearchScreenState(UIToolbar(UIText(), true, UIText()), false, UIList())
    }

    @FlowPreview
    override fun onCreate(fromRecreate: Boolean) {
        searchCityResponseMutableStateFlow
            .debounce(300)
            .map { it.trim() }
            .onEach {

                if (it.isBlank()) {
                    setState { copy(searchList = UIList()) }
                    return@onEach
                }

                setState {
                    copy(showLoading = true)
                }
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
                    searchCityResponseMutableStateFlow.value = intent.city
                }
            }
            is SearchScreenIntent.SearchCitiesModel -> {
                viewModelScope.launch {
                    val value = UICity(
                        intent.woeid,
                        intent.title,
                        UIText(),
                        intent.locationType,
                        UIText(),
                        intent.latitude
                    )
                    favouriteWeatherInteractor.setCityFavourite(value)
                }
            }
        }
    }
}
