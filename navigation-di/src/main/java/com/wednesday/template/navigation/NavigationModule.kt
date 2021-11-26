package com.wednesday.template.navigation

import androidx.fragment.app.Fragment
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.navigation.search.SearchNavigatorImpl
import com.wednesday.template.navigation.start.StartNavigatiorImpl
import com.wednesday.template.navigation.start.StartNavigator
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.get
import org.koin.dsl.module

val navigationModule = module {

    factory<Navigator> { (fragment: Fragment) -> NavigatorImpl(fragment) }

    factory<SearchNavigator> { (fragment: Fragment) ->
        SearchNavigatorImpl(
            get {
                parametersOf(
                    fragment
                )
            }
        )
    }
    factory<StartNavigator> { (fragment: Fragment) ->
        StartNavigatiorImpl(
            get {
                parametersOf(
                    fragment
                )
            }
        )
    }
}
