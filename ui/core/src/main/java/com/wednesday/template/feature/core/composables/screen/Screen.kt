package com.wednesday.template.feature.core.composables.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.wednesday.template.feature.core.effect.Effect
import com.wednesday.template.feature.core.viewmodel.BaseViewModel
import com.wednesday.template.presentation.screen.MainScreenState

data class ScreenContainerData(
    val snackbarHostState: SnackbarHostState
) {
    companion object {
        @Composable
        fun screenContainerData(): ScreenContainerData {
            return ScreenContainerData(
                snackbarHostState = remember {
                    SnackbarHostState()
                }
            )
        }
    }
}

@Composable
fun <STATE : MainScreenState, EFFECT : Effect, VM : BaseViewModel<STATE, EFFECT>> ScreenConnector(
    viewModel: VM,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onEffect: suspend ScreenContainerData.(EFFECT) -> Unit,
    content: @Composable ScreenContainerData.(STATE) -> Unit,
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onComposed()
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    val screenContainerData = remember(snackbarHostState) {
        ScreenContainerData(
            snackbarHostState = snackbarHostState
        )
    }

    LaunchedEffect(viewModel) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.effectState.collect {
                screenContainerData.onEffect(it)
            }
        }
    }

    screenContainerData.content(state)
}
