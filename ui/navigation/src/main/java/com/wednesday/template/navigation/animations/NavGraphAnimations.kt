package com.wednesday.template.navigation.animations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

@OptIn(ExperimentalAnimationApi::class)
fun getNavGraphAnimations(): RootNavGraphDefaultAnimations {
    return RootNavGraphDefaultAnimations(
        enterTransition = { slideInVertically(initialOffsetY = { it / 2 }) + fadeIn() },
        exitTransition = { slideOutVertically(targetOffsetY = { -it / 2 }) + fadeOut() },
        popEnterTransition = { slideInVertically(initialOffsetY = { -it / 2 }) + fadeIn() },
        popExitTransition = { slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut() },
    )
}
