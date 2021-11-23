package com.wednesday.template.presentation.weather.search

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val searchCityInteractor: SearchCityInteractor
) : BaseViewModel<SearchFragmentScreen, SearchFragmentScreenState>(),IntentHandler<SearchScreenIntent> {

    val result:MutableLiveData<UIList> = MutableLiveData()

    override fun getDefaultScreenState(): SearchFragmentScreenState {
        return SearchFragmentScreenState(UIToolbar(UIText(),true, UIText()),false)
    }

    override fun onCreate(fromRecreate: Boolean) {
    }

    override fun onIntent(intent: SearchScreenIntent) {
        when(intent) {
            is SearchScreenIntent.SearchCities -> {
                viewModelScope.launch {
                    result.value = searchCityInteractor.search(intent.city)
                    setState {
                        copy(showLoading=true,searchList = result.value!!)
                    }
                }
            }
        }
    }


}
