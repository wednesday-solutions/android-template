package com.wednesday.template.navigation

import androidx.fragment.app.Fragment
import com.wednesday.template.navigation.home.HomeNavigator
import com.wednesday.template.navigation.home.HomeNavigatorImpl
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.navigation.search.SearchNavigatorImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val navigationModule = module {

    factory<BaseNavigator> { BaseNavigatorImpl(get()) }

    factory<SearchNavigator> { SearchNavigatorImpl(get()) }

    factory<HomeNavigator> { HomeNavigatorImpl(get()) }
}
