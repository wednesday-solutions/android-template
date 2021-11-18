package com.wednesday.template.presentation.weather.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wednesday.template.interactor.weather.SearchCityInteractor
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val searchCityInteractor: SearchCityInteractor
) : ViewModel() {

    fun searchCity(city: String) {
        viewModelScope.launch {
            val cityList = searchCityInteractor.searchCity(city)
            Log.d("$$$$$$$$$$$$", cityList.toString())
        }
    }
}
