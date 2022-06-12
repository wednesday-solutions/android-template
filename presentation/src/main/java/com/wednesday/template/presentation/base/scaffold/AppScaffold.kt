package com.wednesday.template.presentation.base.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wednesday.template.presentation.base.compositionLocals.LocalDialogHostState
import com.wednesday.template.presentation.base.compositionLocals.LocalSnackbarHostState
import com.wednesday.template.presentation.base.dialog.DialogHost
import com.wednesday.template.presentation.base.dialog.DialogHostState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: (@Composable (SnackbarHostState) -> Unit)? = null,
    dialogHost: (@Composable (DialogHostState) -> Unit)? = null,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val dialogHostState = remember { DialogHostState() }

    CompositionLocalProvider(
        LocalDialogHostState provides dialogHostState,
        LocalSnackbarHostState provides snackbarHostState,
    ) {
        Scaffold(
            modifier = modifier,
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = {
                if (snackbarHost != null) {
                    snackbarHost(snackbarHostState)
                } else {
                    SnackbarHost(hostState = snackbarHostState)
                }
            },
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition,
            containerColor = containerColor,
            contentColor = contentColor,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ) {

                // Host Composable to display dialogs
                if (dialogHost != null) {
                    dialogHost(dialogHostState)
                } else {
                    DialogHost(hostState = dialogHostState)
                }

                content()
            }
        }
    }
}
