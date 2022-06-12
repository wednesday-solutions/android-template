package com.wednesday.template.presentation.base.compositionLocals

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSnackbarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error("Composition Local LocalSnackbarHostState not present")
}
