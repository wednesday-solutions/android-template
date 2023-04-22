package com.wednesday.template

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.wednesday.template.design_system.theme.AppTheme
import com.wednesday.template.home.presentation.destinations.HomeScreenDestination
import com.wednesday.template.navigation.animations.getNavGraphAnimations
import com.wednesday.template.navigation.graph.mainNavGraph

class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val engine = rememberAnimatedNavHostEngine(
                    rootDefaultAnimations = getNavGraphAnimations()
                )

                DestinationsNavHost(navGraph = mainNavGraph, engine = engine)
            }
        }
    }
}