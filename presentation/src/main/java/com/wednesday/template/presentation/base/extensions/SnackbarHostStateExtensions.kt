package com.wednesday.template.presentation.base.extensions

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import com.wednesday.template.presentation.base.effect.ShowSnackbarEffect

suspend fun SnackbarHostState.showSnackbar(effect: ShowSnackbarEffect) {
    val result = showSnackbar(
        message = effect.message.asString(),
        actionLabel = effect.action?.asString()
    )

    when (result) {
        SnackbarResult.Dismissed -> effect.onDismiss()
        SnackbarResult.ActionPerformed -> effect.onActionClick()
    }
}
