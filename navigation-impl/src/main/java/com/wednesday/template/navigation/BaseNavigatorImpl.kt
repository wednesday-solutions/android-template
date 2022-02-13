package com.wednesday.template.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.wednesday.template.presentation.screen.Screen

class BaseNavigatorImpl(private val navController: NavHostController) : BaseNavigator {

    override fun navigateTo(screen: Screen) {
        navController.navigate(route = screen.route)
    }

    override fun back() {
        navController.apply {
            // todo Improve back implementation
            if (!navigateUp()) {
                popBackStack()
            }
        }
    }

    private fun Screen.navigate() {
        val navOptions = navOptions {
            anim {
                enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
            }
        }
//        fragment.findNavController().navigate(id, bundleOf("key_args" to this), navOptions)
    }
}
