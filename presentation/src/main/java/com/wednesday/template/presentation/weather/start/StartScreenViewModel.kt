package com.wednesday.template.presentation.weather.start

import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel

class StartScreenViewModel(
    private val searchCityInteractor: SearchCityInteractor,
    private val navigator: SearchNavigator
) : BaseViewModel<StartFragmentScreen, StartScreenState>(),
    IntentHandler<StartScreenIntent> {
    
    override fun getDefaultScreenState(): StartScreenState {
        return StartScreenState(
            toolbar = UIToolbar(
                title = UIText { block("Search") },
                hasBackButton = true,
                menuTitle = null
            ),
            showLoading = false,
        )
    }

    override fun onCreate(fromRecreate: Boolean) {
    
    }

    override fun onIntent(intent: StartScreenIntent) {
        searchCityInteractor
        navigator
    }
}
