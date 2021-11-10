package com.wednesday.template.presentation.base.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wednesday.template.presentation.base.screen.Screen

class NavigatorImpl(private val fragment: Fragment) : Navigator {

    override fun navigateTo(screen: Screen, updateBundle: (Bundle) -> Unit) {
        fragment
            .findNavController()
            .navigate(
                screen.id,
                bundleOf("key_args" to screen).apply {
                    updateBundle(this)
                }
            )
    }

    override fun back() {
        fragment.findNavController().apply {
            if (!popBackStack() && !navigateUp()) {
                fragment.activity?.onBackPressed()
            }
        }
    }
}
