package com.wednesday.template.navigation.home

import com.wednesday.template.navigation.Navigator
import com.wednesday.template.presentation.screen.Screen

class HomeNavigatorImpl(
    private val navigator: Navigator
) : HomeNavigator {

    override fun navigateTo(screen: Screen) {
        navigator.navigateTo(screen)
    }
}
