package com.wednesday.template.navigation.start

import com.wednesday.template.navigation.Navigator
import com.wednesday.template.presentation.screen.Screen

class StartNavigatorImpl(
    private val navigator: Navigator
) : StartNavigator {

    override fun onSearchClick(screen: Screen) {
        navigator.navigateTo(screen)
    }
}
