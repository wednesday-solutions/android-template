package com.wednesday.template.presentation.weather.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.base.viewmodel.BaseViewModel
import com.wednesday.template.presentation.screen.ScreenState
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val searchCityInteractor: SearchCityInteractor
) : BaseViewModel<SearchFragmentScreen, ScreenState>() {

    fun searchCity(city: String) {
        viewModelScope.launch {
            val cityList = searchCityInteractor.searchCity(city)
            Log.d("$$$$$$$$$$$$", cityList.toString())
        }
    }

    override fun getDefaultScreenState(): ScreenState {
        TODO("Not yet implemented")
    }

    override fun onCreate(fromRecreate: Boolean) {
        TODO("Not yet implemented")
    }
}
