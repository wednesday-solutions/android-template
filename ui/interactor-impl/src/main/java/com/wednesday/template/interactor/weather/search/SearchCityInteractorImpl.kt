package com.wednesday.template.interactor.weather.search

import com.wednesday.template.data.core.CoroutineContextController
import com.wednesday.template.domain.base.Result
import com.wednesday.template.domain.weather.City
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCase
import com.wednesday.template.domain.weather.SearchCitiesUseCase
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber

class SearchCityInteractorImpl(
    private val searchCitiesUseCase: SearchCitiesUseCase,
    favouriteCitiesFlowUseCase: GetFavouriteCitiesFlowUseCase,
    private val citySearchResultMapper: UICitySearchResultsMapper,
    private val coroutineContextController: CoroutineContextController,
) : SearchCityInteractor {

    private val searchResultStateFlow = Channel<List<City>>()

    override val searchResultsFlow: Flow<UIResult<UIList>> = favouriteCitiesFlowUseCase(Unit)
        .combine(
            searchResultStateFlow
                .receiveAsFlow()
                .map { it.distinctBy { city -> city.id } },
        ) { favouriteCities, searchResults ->
            when {
                searchResults.isEmpty() -> {
                    UIResult.Success(UIList())
                }
                favouriteCities is Result.Success -> {
                    UIResult.Success(
                        citySearchResultMapper.map(
                            favouriteCities.data,
                            searchResults,
                        ),
                    )
                }
                favouriteCities is Result.Error -> {
                    Timber.e(favouriteCities.exception)
                    UIResult.Error(favouriteCities.exception)
                }
                else -> {
                    error("Something went wrong")
                }
            }
        }
        .onEach {
            Timber.tag(TAG).d("searchResultsFlow: emit = $it")
        }
        .flowOn(coroutineContextController.dispatcherDefault)
        .catch { e ->
            emit(UIResult.Error(e as Exception))
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
        searchResultStateFlow.send(list)
    }

    companion object {
        private const val TAG = "SearchCityInteractorImpl"
    }
}
