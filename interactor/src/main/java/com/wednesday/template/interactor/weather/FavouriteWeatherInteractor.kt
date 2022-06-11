package com.wednesday.template.interactor.weather

import com.wednesday.template.presentation.base.list.UIList
import com.wednesday.template.presentation.base.result.UIResult
import com.wednesday.template.presentation.weather.UICity
import kotlinx.coroutines.flow.Flow

interface FavouriteWeatherInteractor {

    suspend fun setCityFavourite(uiCity: UICity): UIResult<Unit>

    suspend fun removeCityFavourite(uiCity: UICity): UIResult<Unit>

    fun getFavouriteWeatherUIList(): Flow<UIResult<UIList>>

    fun getFavouriteCitiesFlow(): Flow<UIResult<List<UICity>>>

    suspend fun fetchFavouriteCitiesWeather(): UIResult<Unit>
}
