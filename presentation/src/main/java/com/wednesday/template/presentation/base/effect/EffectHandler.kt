package com.wednesday.template.presentation.base.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun EffectHandler(
    effectFlow: Flow<Effect?>,
    onEffect: suspend (Effect) -> EffectResult
) {
    LaunchedEffect(key1 = true) {
        effectFlow
            .filterNotNull()
            .onEach {
                launch {
                    val effectResult = onEffect(it)
                    if (effectResult.isUnhandled) {
                        error("Effect ${it.javaClass.name} was not handled. Please handle it in onEffect.")
                    }
                }
            }
            .launchIn(this)
    }
}
