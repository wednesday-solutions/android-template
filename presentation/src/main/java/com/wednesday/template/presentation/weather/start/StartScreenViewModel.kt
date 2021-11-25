package com.wednesday.template.presentation.weather.start

import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel

class StartScreenViewModel : BaseViewModel<
    StartFragmentScreen, StartScreenState>(), IntentHandler<StartScreenIntent> {
    
    
    
    override fun getDefaultScreenState(): StartScreenState {
        TODO()
    }
    
    override fun onCreate(fromRecreate: Boolean) {
        TODO("Not yet implemented")
    }
    
    override fun onIntent(intent: StartScreenIntent) {
        TODO("Not yet implemented")
    }
}