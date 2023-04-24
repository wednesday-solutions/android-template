package com.wednesday.template.interactor.weather

import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIResult
import kotlinx.coroutines.flow.Flow

interface SearchCityInteractor {

    val searchResultsFlow: Flow<UIResult<UIList>>

    suspend fun search(term: String)
}
