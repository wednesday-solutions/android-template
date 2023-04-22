package com.wednesday.template.interactor.weather.favourite

import com.wednesday.template.domain.weather.FetchFavouriteCitiesWeatherUseCase
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCase
import com.wednesday.template.domain.weather.GetFavouriteCitiesWeatherFlowUseCase
import com.wednesday.template.domain.weather.RemoveCityFavouriteUseCase
import com.wednesday.template.domain.weather.SetCityFavouriteUseCase
import com.wednesday.template.interactor.base.BaseInteractor
import com.wednesday.template.interactor.base.CoroutineContextController
import com.wednesday.template.interactor.base.mapToUIResult
import com.wednesday.template.interactor.weather.FavouriteWeatherInteractor
import com.wednesday.template.interactor.weather.UICityMapper
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIResult
import com.wednesday.template.presentation.weather.UICity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class FavouriteWeatherInteractorImpl(
    private val setCityFavouriteUseCase: SetCityFavouriteUseCase,
    private val removeCityFavouriteUseCase: RemoveCityFavouriteUseCase,
    private val getFavouriteCitiesFlowUseCase: GetFavouriteCitiesFlowUseCase,
    private val getFavouriteCitiesWeatherFlowUseCase: GetFavouriteCitiesWeatherFlowUseCase,
    private val fetchFavouriteCitiesWeatherUseCase: FetchFavouriteCitiesWeatherUseCase,
    private val uiCityMapper: UICityMapper,
    private val weatherListMapper: UIWeatherListMapper,
    private val coroutineContextController: CoroutineContextController
) : BaseInteractor(), FavouriteWeatherInteractor {

    override suspend fun setCityFavourite(uiCity: UICity): UIResult<Unit> =
        coroutineContextController.switchToDefault {
            Timber.tag(TAG).d("setCityFavourite: city = $uiCity")
            val city = uiCityMapper.mapUICity(uiCity)
            setCityFavouriteUseCase(city).let(::mapResult)
        }

    override suspend fun removeCityFavourite(uiCity: UICity): UIResult<Unit> =
        coroutineContextController.switchToDefault {
            Timber.tag(TAG).d("removeCityFavourite: city = $uiCity")
            val city = uiCityMapper.mapUICity(uiCity)
            removeCityFavouriteUseCase(city).let(::mapResult)
        }

    override fun getFavouriteWeatherUIList(): Flow<UIResult<UIList>> {
        Timber.tag(TAG).d("getFavouriteCitiesFlow")
        return getFavouriteCitiesWeatherFlowUseCase(Unit)
            .distinctUntilChanged()
            .mapToUIResult(success = {
                weatherListMapper.map(this.data)
            })
            .flowOn(coroutineContextController.dispatcherDefault)
            .onEach { Timber.tag(TAG).d("getFavouriteWeatherUIList: emit = $it") }
    }

    override fun getFavouriteCitiesFlow(): Flow<UIResult<List<UICity>>> {
        Timber.tag(TAG).d("getFavouriteCitiesFlow() called")
        return getFavouriteCitiesFlowUseCase(Unit)
            .mapToUIResult(success = {
                this.data.map(uiCityMapper::mapFavouriteCity)
            })
            .flowOn(coroutineContextController.dispatcherDefault)
            .onEach { Timber.tag(TAG).d("getFavouriteCitiesFlow: emit = $it") }
    }

    override suspend fun fetchFavouriteCitiesWeather(): UIResult<Unit> =
        coroutineContextController.switchToDefault {
            Timber.tag(TAG).d("fetchFavouriteCities() called")
            fetchFavouriteCitiesWeatherUseCase(Unit).let(::mapResult)
        }

    companion object {
        private const val TAG = "FavouriteWeatherInteractorImpl"
    }
}
