package com.wednesday.template.presentation.weather.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.presentation.R
import com.wednesday.template.presentation.base.effect.ShowAlertDialogEffect
import com.wednesday.template.presentation.base.effect.ShowSnackbarEffect
import com.wednesday.template.presentation.base.extensions.asUIText
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.list.UIList
import com.wednesday.template.presentation.base.result.UIResult
import com.wednesday.template.presentation.base.text.UIText
import com.wednesday.template.presentation.base.toolbar.UIToolbar
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val favouriteWeatherInteractor: FavouriteWeatherInteractor,
) : BaseViewModel<HomeScreen, HomeScreenState>(),
    IntentHandler<HomeScreenIntent> {

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

    override fun onCreate(fromRecreate: Boolean) {
        if (fromRecreate) return
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
                        ShowSnackbarEffect(
                            UIText {
                                block(R.string.something_went_wrong)
                            }
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.Search -> {
                setEffect(ShowSnackbarEffect("Hello".asUIText()))
            }
            HomeScreenIntent.Loading -> {
                setEffect(
                    ShowAlertDialogEffect(
                        icon = Icons.Filled.Album,
                        title = "Alert title".asUIText(),
                        text = "Description text".asUIText(),
                        confirmButtonText = "Confirm".asUIText(),
                        onConfirmButtonClicked = {
                            it.dismissDialog()
                        }
                    )
                )
            }
        }
    }
}
