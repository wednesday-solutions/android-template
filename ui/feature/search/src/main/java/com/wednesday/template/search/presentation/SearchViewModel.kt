package com.wednesday.template.search.presentation

import androidx.lifecycle.viewModelScope
import com.wednesday.template.feature.core.effect.SnackbarEffectData
import com.wednesday.template.feature.core.viewmodel.BaseViewModel
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIResult
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.presentation.weather.search.SearchScreenIntent
import com.wednesday.template.search.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchCityInteractor: SearchCityInteractor,
    private val favouriteWeatherInteractor: FavouriteWeatherInteractor,
) : BaseViewModel<SearchScreenState, SearchScreenEffect>(),
    IntentHandler<SearchScreenIntent> {

    private val searchCityResponseMutableStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    override fun getDefaultScreenState(): SearchScreenState {
        return SearchScreenState(
            toolbar = UIToolbar(
                title = UIText { block("Search") },
                hasBackButton = true,
                menuIcon = null,
            ),
            showLoading = false,
            searchList = UIList(),
        )
    }

    init {
        searchCityInteractor.searchResultsFlow.onEach {
            when (it) {
                is UIResult.Success -> {
                    setState {
                        copy(showLoading = false, searchList = it.data)
                    }
                }

                is UIResult.Error -> {
                    setState {
                        copy(showLoading = false)
                    }
                    setEffect(
                        SearchScreenEffect.ShowSnackbarEffect(
                            SnackbarEffectData(
                                UIText {
                                    block(R.string.something_went_wrong)
                                },
                            ),
                        ),
                    )
                }
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

    fun onFavouriteClick(uiCity: UICity) {
        onIntent(SearchScreenIntent.ToggleFavourite(uiCity))
    }

    fun search(city: String) {
        setState {
            copy(searchText = city)
        }
        onIntent(SearchScreenIntent.SearchCities(city))
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

            SearchScreenIntent.Back -> {
                setEffect(SearchScreenEffect.NavigateBack)
            }
        }
    }
}
