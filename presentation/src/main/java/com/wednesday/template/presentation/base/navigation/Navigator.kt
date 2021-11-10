package com.wednesday.template.presentation.base.navigation

import android.os.Bundle
import com.wednesday.template.presentation.base.screen.Screen

interface Navigator {

    fun navigateTo(screen: Screen, updateBundle: ((Bundle) -> Unit) = {})

    fun back()
}
