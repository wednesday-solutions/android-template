package com.wednesday.template.interactor.weather.search

import com.wednesday.template.domain.base.Result
import com.wednesday.template.domain.weather.City
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCase
import com.wednesday.template.domain.weather.SearchCitiesUseCase
import com.wednesday.template.interactor.base.CoroutineContextController
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.interactor.weather.UICityMapper
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.weather.UICity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.zip
import timber.log.Timber

class SearchCityInteractorImpl(
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val favouriteCitiesFlowUseCase: GetFavouriteCitiesFlowUseCase,
    private val citySearchResultMapper: UICitySearchResultsMapper,
    private val coroutineContextController: CoroutineContextController
) : SearchCityInteractor {

    private val searchResultStateFlow = MutableStateFlow<List<City>>(listOf())

    override val searchResultsFlow: Flow<UIList> = favouriteCitiesFlowUseCase(Unit)
        .zip(searchResultStateFlow) { favoriteCites, searchResults ->
            citySearchResultMapper.map(favoriteCites, searchResults)
        }

    override suspend fun search(term: String): Unit = coroutineContextController.switchToDefault {
        Timber.tag(TAG).d("search: term = $term")
        val list = when (val citiesResult = searchCitiesUseCase(term)) {
            is Result.Error -> {
                Timber.tag(TAG).e(citiesResult.exception, "search error")
                listOf()
            }
            is Result.Success -> citiesResult.data
        }
        searchResultStateFlow.emit(list)
    }

    companion object {
        private const val TAG = "SearchCityInteractorImpl"
    }
}
