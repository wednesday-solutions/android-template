package com.wednesday.template.presentation.weather.search

import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val searchCityInteractor: SearchCityInteractor,
    private val favouriteWeatherInteractor: FavouriteWeatherInteractor,
    private val navigator: SearchNavigator,
) : BaseViewModel<SearchFragmentScreen, SearchScreenState>(),
    IntentHandler<SearchScreenIntent> {

    private val searchCityResponseMutableStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override fun getDefaultScreenState(): SearchScreenState {
        return SearchScreenState(
            toolbar = UIToolbar(
                title = UIText { block("Search") },
                hasBackButton = true,
                menuTitle = null
            ),
            showLoading = false,
            searchList = UIList()
        )
    }

    @FlowPreview
    override fun onCreate(fromRecreate: Boolean) {

        searchCityInteractor.searchResultsFlow.onEach {
            setState {
                copy(showLoading = false, searchList = it)
            }
        }.launchIn(viewModelScope)

        searchCityResponseMutableStateFlow
            .debounce(500)
            .map { it.trim() }
            .onEach {

                if (it.isBlank()) {
                    setState { copy(searchList = UIList()) }
                    return@onEach
                }

                setState {
                    copy(showLoading = true)
                }
                searchCityInteractor.search(it)
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
            is SearchScreenIntent.ToggleFavourite -> {
                viewModelScope.launch {
                    if (intent.city.isFavourite) {
                        favouriteWeatherInteractor.removeCityFavourite(intent.city)
                    } else {
                        favouriteWeatherInteractor.setCityFavourite(intent.city)
                    }
                }
            }
            SearchScreenIntent.Back -> navigator.back()
        }
    }
}
