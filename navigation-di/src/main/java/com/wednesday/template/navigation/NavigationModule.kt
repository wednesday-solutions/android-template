package com.wednesday.template.navigation

import androidx.fragment.app.Fragment
import org.koin.dsl.module

val navigationModule = module {

    factory<Navigator> { (fragment: Fragment) -> NavigatorImpl(fragment) }

}