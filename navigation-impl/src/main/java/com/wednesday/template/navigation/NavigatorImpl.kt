package com.wednesday.template.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.wednesday.template.presentation.screen.Screen

class NavigatorImpl(private val fragment: Fragment) : Navigator {

    override fun navigateTo(screen: Screen) {
        screen.navigate()
    }

    override fun back() {
        fragment.findNavController().apply {
            if (!popBackStack() && !navigateUp()) {
                fragment.activity?.onBackPressed()
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
        fragment.findNavController().navigate(id, bundleOf("key_args" to this), navOptions)
    }
}
