package com.wednesday.template.interactor.weather

import com.wednesday.template.presentation.base.UIList
import kotlinx.coroutines.flow.Flow

interface SearchCityInteractor {

    val searchResultsFlow: Flow<UIList>

    suspend fun search(term: String)
}
