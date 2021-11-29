package com.wednesday.template.navigation.home

import android.util.Log
import com.wednesday.template.navigation.BaseNavigator
import com.wednesday.template.presentation.screen.Screen

class HomeNavigatorImpl(
    private val baseNavigator: BaseNavigator
) : HomeNavigator {

    init {
        Log.d("HomeNavigatorImpl", "navViewModel init $this")
    }

    override fun navigateTo(screen: Screen) {
        baseNavigator.navigateTo(screen)
    }
}
