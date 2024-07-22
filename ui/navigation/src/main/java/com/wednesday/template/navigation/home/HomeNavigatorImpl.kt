package com.wednesday.template.navigation.home

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wednesday.template.home.presentation.HomeNavigator
import com.wednesday.template.search.presentation.destinations.SearchScreenDestination

class HomeNavigatorImpl(
    private val destinationsNavigator: DestinationsNavigator,
) : HomeNavigator {
    override fun navigateToSearch() {
        destinationsNavigator.navigate(SearchScreenDestination)
    }
}
