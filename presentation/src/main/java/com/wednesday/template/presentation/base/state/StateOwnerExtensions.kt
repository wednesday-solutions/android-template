package com.wednesday.template.presentation.base.state

fun <T> StateOwner.stateful(defaultValueProvider: (() -> T)? = null): Stateful<T> {
    return Stateful(savedStateHandle, defaultValueProvider)
}

fun <T> StateOwner.statefulLiveData(defaultValueProvider: (() -> T)? = null): StatefulLiveData<T> {
    return StatefulLiveData(savedStateHandle, defaultValueProvider)
}
