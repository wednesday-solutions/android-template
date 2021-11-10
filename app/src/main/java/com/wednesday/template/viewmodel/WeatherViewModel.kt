package com.wednesday.template.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class WeatherViewModel(application: Application) : AndroidViewModel(application), DIAware {

    override val di by di()

//    private val databaseDao: DatabaseDao by instance("databaseDao")
//    private val apiService: WeatherApiService by instance("apiService")
//    val weatherLiveData = MutableLiveData(Resource.success(listOf<Weather>()))

    fun triggerLoadForAllFavoriteCities() {
//        viewModelScope.launch {
//            weatherLiveData.value = Resource.loading(listOf())
//
//            val cities = databaseDao.getFavoriteCities()
//            val liveDataWeatherValues = weatherLiveData.value!!.data!!.toMutableList()
//
//            cities.forEach { city ->
//                val weather = apiService.weatherForCity(city.woeid)
//                val liveDataWeather =
//                    liveDataWeatherValues.find { value -> value.title == weather.title }
//                if (liveDataWeather !== null) {
//                    liveDataWeather.consolidatedWeathers = weather.consolidatedWeathers
//                } else {
//                    liveDataWeatherValues.add(weather)
//                }
//                weatherLiveData.value = Resource.loading(liveDataWeatherValues)
//            }
//            weatherLiveData.value = Resource.success(liveDataWeatherValues)
//        }
    }
}
