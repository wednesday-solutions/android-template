package com.wednesday.template.feature.core.composables.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.wednesday.template.data.core.SuspendUnitCallback
import com.wednesday.template.data.core.UnitCallback
import com.wednesday.template.design_system.composables.page.UIPage
import com.wednesday.template.feature.core.R
import com.wednesday.template.feature.core.effect.Effect
import com.wednesday.template.feature.core.extensions.asString
import com.wednesday.template.feature.core.viewmodel.BaseViewModel
import com.wednesday.template.presentation.screen.MainScreenState

@Composable
fun <STATE : MainScreenState, EFFECT: Effect, VM : BaseViewModel<STATE, EFFECT>> Screen(
    viewModel: VM,
    onEffect: (EFFECT) -> Unit,
    onBackPress: UnitCallback,
    onComposed: SuspendUnitCallback = {},
    content: @Composable STATE.(PaddingValues) -> Unit,
) {
    val state by viewModel.collectAsState()

    UIPage(
        title = state.toolbar.title.asString(),
        hasBackButton = state.toolbar.hasBackButton,
        onBackPress = onBackPress,
        backButtonContentDescription = stringResource(id = R.string.acc_navigate_back)
    ) { paddingValues ->

        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(Unit) {
            onComposed()
        }

        LaunchedEffect(viewModel) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effectState.collect(onEffect)
            }
        }

        state.content(paddingValues)
    }
}