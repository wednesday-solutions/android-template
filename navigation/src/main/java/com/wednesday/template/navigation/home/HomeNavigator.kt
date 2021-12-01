package com.wednesday.template.navigation.home

import com.wednesday.template.navigation.Navigator
import com.wednesday.template.presentation.screen.Screen

interface HomeNavigator : Navigator {

    fun navigateTo(screen: Screen)
}
