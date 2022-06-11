package com.wednesday.template.presentation.base.effect

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.wednesday.template.presentation.base.dialog.DialogHostState
import com.wednesday.template.presentation.base.extensions.showSnackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun EffectHandler(
    effectFlow: Flow<Effect?>,
    snackbarHostState: SnackbarHostState,
    dialogHostState: DialogHostState
) {
    LaunchedEffect(key1 = true) {
        effectFlow.collect {
            when (it) {
                is ShowSnackbarEffect -> launch {
                    // If called without launch, will block any other effects from processing
                    // until snackbar is dismissed.
                    snackbarHostState.showSnackbar(it)
                }
                is ShowAlertDialogEffect -> dialogHostState.showDialog(it)
            }
        }
    }
}
