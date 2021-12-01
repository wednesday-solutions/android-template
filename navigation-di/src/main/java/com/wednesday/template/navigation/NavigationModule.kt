package com.wednesday.template.navigation

import androidx.fragment.app.Fragment
import com.wednesday.template.navigation.home.HomeNavigator
import com.wednesday.template.navigation.home.HomeNavigatorImpl
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.navigation.search.SearchNavigatorImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val navigationModule = module {

    factory<BaseNavigator> { (fragment: Fragment) -> BaseNavigatorImpl(fragment) }

    factory<SearchNavigator> { (fragment: Fragment) ->
        SearchNavigatorImpl(
            get {
                parametersOf(
                    fragment
                )
            }
        )
    }
    factory<HomeNavigator> { (fragment: Fragment) ->
        HomeNavigatorImpl(
            get {
                parametersOf(
                    fragment
                )
            }
        )
    }
}
