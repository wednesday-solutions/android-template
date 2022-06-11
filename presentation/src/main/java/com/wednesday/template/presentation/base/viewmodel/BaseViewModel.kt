package com.wednesday.template.presentation.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.state.StateOwner
import com.wednesday.template.presentation.base.state.statefulLiveData
import com.wednesday.template.presentation.screen.Screen
import com.wednesday.template.presentation.screen.ScreenState
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel<SCREEN : Screen, STATE : ScreenState> :
    ViewModel(), KoinComponent, StateOwner {

    abstract fun getDefaultScreenState(): STATE

    override val savedStateHandle by inject<SavedStateHandle>()

    private val _screenState by statefulLiveData<STATE?> { null }
    val screenState: LiveData<STATE?> = _screenState

    private val _effectState = MutableLiveData<Effect?>(null)
    val effectState: Flow<Effect?> = _effectState.asFlow()

    private val _screenResultState = MutableLiveData<Bundle?>(null)
    val screenResultState: LiveData<Bundle?> = _screenResultState

    lateinit var args: SCREEN

    private var recreateFlag: Unit? = null

    internal abstract fun onCreate(fromRecreate: Boolean)

    open fun onResume() = Unit

    open fun onDestroyView() = Unit

    fun onCreate(bundle: Bundle?) {
        val isFreshCreate = bundle == null
        val isFromRecreate = recreateFlag == null
        recreateFlag = Unit
        if (isFromRecreate) {
            _screenState.value = getDefaultScreenState()
        }
        if (isFreshCreate) {
            onCreate(fromRecreate = false)
        } else if (isFromRecreate) {
            onCreate(fromRecreate = true)
        }
    }

    protected fun setState(reducer: STATE.() -> STATE) {
        _screenState.value?.let {
            _screenState.value = it.reducer()
        }
    }

    protected fun setEffect(effect: Effect) {
        _effectState.value = effect
        _effectState.value = null
    }

    protected fun setScreenResult(bundle: Bundle) {
        _screenResultState.value = bundle
        _screenResultState.value = null
    }

    protected fun unhandledIntent(intent: Intent) {
        throw IllegalStateException(
            "Intent of type $intent is not handled by ${this.javaClass.name}." +
                    " If you want to handle this intent then add support in when clause"
        )
    }

    open fun clearState() = Unit
}
