package com.wednesday.template.presentation.base.state

import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Stateful<T>(
    private val savedStateHandle: SavedStateHandle,
    private val defaultValueProvider: (() -> T)? = null
) : ReadWriteProperty<Any, T?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return savedStateHandle.get<T>(property.name) ?: defaultValueProvider?.invoke()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        savedStateHandle.set(property.name, value)
    }
}
