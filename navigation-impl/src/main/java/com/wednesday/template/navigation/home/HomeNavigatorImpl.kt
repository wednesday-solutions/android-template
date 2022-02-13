package com.wednesday.template.navigation.home

import android.util.Log
import com.wednesday.template.navigation.BaseNavigator
import com.wednesday.template.presentation.screen.Screen
import com.wednesday.template.presentation.screen.SearchScreen

class HomeNavigatorImpl(
    private val baseNavigator: BaseNavigator
) : HomeNavigator {

    override fun navigateToSearch(screen: SearchScreen) {
        baseNavigator.navigateTo(screen)
    }
}
