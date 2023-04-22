package com.wednesday.template.navigation.animations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

@OptIn(ExperimentalAnimationApi::class)
fun getNavGraphAnimations(): RootNavGraphDefaultAnimations {
    return RootNavGraphDefaultAnimations(
        enterTransition = { slideInVertically(animationSpec = tween(350)) + fadeIn() },
        exitTransition = { slideOutVertically(animationSpec = tween(350)) + fadeOut() }
    )
}