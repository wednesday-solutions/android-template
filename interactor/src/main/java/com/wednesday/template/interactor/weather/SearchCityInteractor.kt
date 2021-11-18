package com.wednesday.template.interactor.weather

import com.wednesday.template.presentation.base.UIList

interface SearchCityInteractor {

    suspend fun search(term: String): UIList

    suspend fun searchCity(city: String) = listOf("Pune", "Mumbai", "Surat")
}
