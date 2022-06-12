package com.wednesday.template.presentation.base.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Use the [EffectHandler] to opt into handling effects.
 *
 * Creates a [LaunchedEffect] to collect the [effectFlow] and call [onEffect] on every non-null effect emit.
 *
 * @param effectFlow Flow of effects.
 *
 * @param onEffect [onEffect] will be called when an effect is emitted.
 * It should return [EffectResult.HANDLED] to indicate that the effect was handled.
 *
 * If [onEffect] returns [EffectResult.UNHANDLED], an exception will be thrown.
 *
 * You should use [unhandledEffect] to safeguard against unhandled effects.
 * ```
 * when (effect) {
 *    is MyEffect -> myEffectHandler.handle(effect)
 *    else -> unhandledEffect()
 * }
 * ```
 *
 * @throws IllegalStateException if [onEffect] returns [EffectResult.UNHANDLED].
 */
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
