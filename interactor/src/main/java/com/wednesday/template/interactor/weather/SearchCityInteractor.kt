package com.wednesday.template.interactor.weather

import com.wednesday.template.presentation.base.UIList

interface SearchCityInteractor {

    suspend fun search(term: String): UIList

}
