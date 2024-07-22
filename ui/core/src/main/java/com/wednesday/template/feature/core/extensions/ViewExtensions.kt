package com.wednesday.template.feature.core.extensions

import androidx.compose.material3.SnackbarHostState
import com.wednesday.template.feature.core.effect.SnackbarEffectData

suspend fun SnackbarHostState.showSnackbar(snackbarEffectData: SnackbarEffectData) {
    showSnackbar(
        message = snackbarEffectData.message.asString(),
    )
}
