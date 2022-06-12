package com.wednesday.template.presentation.base.compositionLocals

import androidx.compose.runtime.staticCompositionLocalOf
import com.wednesday.template.presentation.base.dialog.DialogHostState

val LocalDialogHostState = staticCompositionLocalOf<DialogHostState> {
    error("Composition Local LocalDialogHostState not present")
}