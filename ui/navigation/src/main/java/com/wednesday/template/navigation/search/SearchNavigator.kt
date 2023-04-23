package com.wednesday.template.navigation.search

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wednesday.template.search.presentation.SearchNavigator

class SearchNavigatorImpl(
    private val destinationsNavigator: DestinationsNavigator
) : SearchNavigator {
    override fun navigateBack() {
        destinationsNavigator.navigateUp()
    }

}
