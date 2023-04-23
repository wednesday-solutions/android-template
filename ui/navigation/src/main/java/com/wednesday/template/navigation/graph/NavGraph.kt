package com.wednesday.template.navigation.graph

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import com.wednesday.template.home.presentation.destinations.HomeScreenDestination
import com.wednesday.template.home.presentation.homeDestinations
import com.wednesday.template.search.presentation.searchDestinations

val mainNavGraph = object : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = buildList {
        addAll(homeDestinations)
        addAll(searchDestinations)
    }.associateBy { it.route }

    override val route: String = "app"

    override val startRoute: Route = HomeScreenDestination
}
