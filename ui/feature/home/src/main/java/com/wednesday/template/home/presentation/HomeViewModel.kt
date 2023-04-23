package com.wednesday.template.home.presentation

import androidx.lifecycle.viewModelScope
import com.wednesday.template.feature.core.effect.SnackbarEffectData
import com.wednesday.template.feature.core.viewmodel.BaseViewModel
import com.wednesday.template.home.R
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIResult
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val favouriteWeatherInteractor: FavouriteWeatherInteractor,
) : BaseViewModel<HomeScreenState, HomeScreenEffect>(),
    IntentHandler<HomeScreenIntent> {

    init {
        favouriteWeatherInteractor.getFavouriteCitiesFlow().onEach {
            favouriteWeatherInteractor.fetchFavouriteCitiesWeather()
        }.launchIn(viewModelScope)

        favouriteWeatherInteractor.getFavouriteWeatherUIList().onEach {
            when (it) {
                is UIResult.Success -> {
                    setState { copy(showLoading = false, items = it.data) }
                }

                is UIResult.Error -> {
                    setEffect(
                        HomeScreenEffect.ShowSnackbarEffect(
                            SnackbarEffectData(
                                message = UIText {
                                    block(R.string.something_went_wrong)
                                }
                            )
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun getDefaultScreenState(): HomeScreenState {
        return HomeScreenState(
            toolbar = UIToolbar(
                title = UIText { block("Weather") },
                hasBackButton = false,
                menuIcon = R.drawable.ic_search,
            ),
            showLoading = false,
            items = UIList()
        )
    }

    fun navigateToSearch() = onIntent(HomeScreenIntent.Search)

    override fun onIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.Search -> {
                setEffect(HomeScreenEffect.NavigateToSearch)
            }

            HomeScreenIntent.Loading -> {
                setState { copy(showLoading = !showLoading) }
            }
        }
    }
}
