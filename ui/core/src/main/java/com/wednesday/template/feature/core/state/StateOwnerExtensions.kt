package com.wednesday.template.feature.core.state

fun <T> StateOwner.stateFlow(defaultValueProvider: () -> T): StatefulStateFlow<T> {
    return StatefulStateFlow(savedStateHandle, defaultValueProvider)
}

fun <T> StateOwner.stateful(defaultValueProvider: (() -> T)? = null): Stateful<T> {
    return Stateful(savedStateHandle, defaultValueProvider)
}