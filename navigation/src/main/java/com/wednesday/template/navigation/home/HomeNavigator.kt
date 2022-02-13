package com.wednesday.template.navigation.home

import com.wednesday.template.navigation.Navigator
import com.wednesday.template.presentation.screen.Screen
import com.wednesday.template.presentation.screen.SearchScreen

interface HomeNavigator : Navigator {

    fun navigateToSearch(screen: SearchScreen)
}
