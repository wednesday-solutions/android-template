package com.wednesday.template.feature.core.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.wednesday.template.feature.core.effect.Effect
import com.wednesday.template.feature.core.state.StateOwner
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.screen.ScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel<STATE : ScreenState, EFFECT: Effect> :
    ViewModel(), KoinComponent, StateOwner {

    private companion object {
        const val STATE_KEY = "screen_state"
    }

    final override val savedStateHandle by inject<SavedStateHandle>()

    val screenState: StateFlow<STATE> =
        savedStateHandle.getStateFlow(STATE_KEY, this.getDefaultScreenState())
    private fun setScreenState(value: STATE) {
        savedStateHandle[STATE_KEY] = value
    }

    private val _effectState = Channel<EFFECT?>(Channel.BUFFERED)
    val effectState: Flow<EFFECT> = _effectState.consumeAsFlow().filterNotNull()

    private var recreateFlag: Unit? = null

    abstract fun getDefaultScreenState(): STATE
    protected abstract fun onCreate(fromRecreate: Boolean)

    fun onCreate() {
        val isFromRecreate = recreateFlag == null
        recreateFlag = Unit
        if (isFromRecreate) {
            setScreenState(getDefaultScreenState())
        }

        onCreate(fromRecreate = isFromRecreate)
    }

    @Composable
    fun collectAsState(): State<STATE> {
        return screenState.collectAsStateWithLifecycle()
    }

    protected fun setState(reducer: STATE.() -> STATE) {
        setScreenState(screenState.value.reducer())
    }

    protected fun setEffect(effect: EFFECT) = viewModelScope.launch {
        _effectState.send(effect)
        _effectState.send(null)
    }

    protected fun unhandledIntent(intent: Intent) {
        throw IllegalStateException(
            "Intent of type $intent is not handled by ${this.javaClass.name}." +
                    " If you want to handle this intent then add support in when clause"
        )
    }
}
