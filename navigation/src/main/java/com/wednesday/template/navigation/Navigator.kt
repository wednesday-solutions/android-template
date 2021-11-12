package com.wednesday.template.navigation

import com.wednesday.template.presentation.screen.Screen

interface Navigator {

    fun navigateTo(screen: Screen)

    fun back()

}