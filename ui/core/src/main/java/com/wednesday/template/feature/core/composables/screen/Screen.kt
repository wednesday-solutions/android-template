package com.wednesday.template.feature.core.composables.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.wednesday.template.data.core.UnitCallback
import com.wednesday.template.designSystem.composables.page.UIPage
import com.wednesday.template.feature.core.R
import com.wednesday.template.feature.core.effect.Effect
import com.wednesday.template.feature.core.extensions.asString
import com.wednesday.template.feature.core.viewmodel.BaseViewModel
import com.wednesday.template.presentation.screen.MainScreenState

data class ScreenInteractionStates(
    val snackbarHostState: SnackbarHostState,
)

@Composable
fun <STATE : MainScreenState, EFFECT : Effect, VM : BaseViewModel<STATE, EFFECT>> Screen(
    viewModel: VM,
    onEffect: suspend ScreenInteractionStates.(EFFECT) -> Unit,
    onToolbarBackPressed: UnitCallback,
    toolbarActions: @Composable RowScope.() -> Unit = {},
    content: @Composable STATE.(PaddingValues) -> Unit,
) {
    val state by viewModel.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    UIPage(
        title = state.toolbar.title.asString(),
        snackbarHostState = snackbarHostState,
        hasBackButton = state.toolbar.hasBackButton,
        onBackPress = onToolbarBackPressed,
        backButtonContentDescription = stringResource(id = R.string.acc_navigate_back),
        toolbarActions = toolbarActions,
    ) { paddingValues ->

        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(Unit) {
            viewModel.onComposed()
        }

        LaunchedEffect(viewModel) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val screenInteractionStates = ScreenInteractionStates(
                    snackbarHostState = snackbarHostState,
                )
                viewModel.effectState.collect {
                    screenInteractionStates.onEffect(it)
                }
            }
        }

        state.content(paddingValues)
    }
}
