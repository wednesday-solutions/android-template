package com.wednesday.template.feature.core.state

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty

class StatefulStateFlow<T>(
    private val savedStateHandle: SavedStateHandle,
    private val defaultValueProvider: (() -> T)
): DistinctReadWriteProperty<StateFlow<T>, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): StateFlow<T> {
        return savedStateHandle.getStateFlow(property.name, defaultValueProvider.invoke())
    }

    override fun set(thisRef: Any, property: KProperty<*>, value: T) {
        savedStateHandle[property.name] = value
    }
}

interface DistinctReadWriteProperty<G,S> {
    operator fun getValue(thisRef: Any, property: KProperty<*>): G

    operator fun set(thisRef: Any, property: KProperty<*>, value: S)
}