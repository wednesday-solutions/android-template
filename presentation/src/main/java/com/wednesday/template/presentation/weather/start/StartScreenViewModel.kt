package com.wednesday.template.presentation.weather.start

import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.navigation.start.StartNavigator
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.weather.UICity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class StartScreenViewModel(
    private val favouriteWeatherInteractor: FavouriteWeatherInteractor,
    private val navigator: StartNavigator
) : BaseViewModel<StartFragmentScreen, StartScreenState>(),
    IntentHandler<StartScreenIntent> {

    private val favouriteWeatherCityMutableStateFlow: MutableStateFlow<Flow<List<UICity>>> = MutableStateFlow(
        flowOf()
    )

    override fun getDefaultScreenState(): StartScreenState {
        return StartScreenState(
            toolbar = UIToolbar(
                title = UIText { block("Search") },
                hasBackButton = false,
                menuTitle = null
            ),
            showLoading = false
        )
    }

    override fun onCreate(fromRecreate: Boolean) {

        favouriteWeatherInteractor.getFavouriteCitiesFlow().onEach {
            setState {
                copy(showLoading = true)
            }
        }.launchIn(viewModelScope)

        favouriteWeatherCityMutableStateFlow.onEach { it ->
            // Call Api for every city
//            it.collect {
//                it.onEach {
//                }
//            }
//            setState {
//                copy(showLoading = false)
//            }
        }
    }

    override fun onIntent(intent: StartScreenIntent) {
        when (intent) {

            is StartScreenIntent.FavoriteCities -> {
                favouriteWeatherCityMutableStateFlow.value = favouriteWeatherInteractor.getFavouriteCitiesFlow()
            }

            is StartScreenIntent.OnClickSearchCities -> {
                navigator.onSearchClick(intent.screen)
            }
        }
    }
}
