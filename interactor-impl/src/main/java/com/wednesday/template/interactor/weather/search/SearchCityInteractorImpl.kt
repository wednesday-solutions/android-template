package com.wednesday.template.interactor.weather.search

import com.wednesday.template.domain.base.Result
import com.wednesday.template.domain.weather.SearchCitiesUseCase
import com.wednesday.template.interactor.base.CoroutineContextController
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.interactor.weather.UICityMapper
import com.wednesday.template.presentation.base.UIList
import timber.log.Timber

class SearchCityInteractorImpl(
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val uiCityMapper: UICityMapper,
    private val coroutineContextController: CoroutineContextController
) : SearchCityInteractor {

    override suspend fun search(term: String): UIList = coroutineContextController.switchToDefault {
        Timber.tag(TAG).d("search: term = $term")
        when (val citiesResult = searchCitiesUseCase(term)) {
            is Result.Error -> {
                Timber.tag(TAG).e(citiesResult.exception, "search error")
                UIList()
            }
            is Result.Success -> UIList(uiCityMapper.map(citiesResult.data))
        }
    }

    companion object {
        private const val TAG = "SearchCityInteractorImpl"
    }
}