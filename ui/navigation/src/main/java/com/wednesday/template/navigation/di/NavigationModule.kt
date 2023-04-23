package com.wednesday.template.navigation.di

import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.wednesday.template.navigation.home.HomeNavigatorImpl
import com.wednesday.template.navigation.search.SearchNavigatorImpl

fun DependenciesContainerBuilder<*>.navigationDependencies() {
    dependency(HomeNavigatorImpl(destinationsNavigator))
    dependency(SearchNavigatorImpl(destinationsNavigator))
}
