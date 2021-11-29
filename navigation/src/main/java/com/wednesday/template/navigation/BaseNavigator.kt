package com.wednesday.template.navigation

import com.wednesday.template.presentation.screen.Screen

interface BaseNavigator : Navigator {

    fun navigateTo(screen: Screen)

    fun back()
}
