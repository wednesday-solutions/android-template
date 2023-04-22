package com.wednesday.template.feature.core.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class StatefulLiveData<T>(
    private val savedStateHandle: SavedStateHandle,
    private val defaultValueProvider: (() -> T)? = null
) : ReadOnlyProperty<Any, MutableLiveData<T?>> {

    override fun getValue(thisRef: Any, property: KProperty<*>): MutableLiveData<T?> {
        return savedStateHandle.getLiveData(property.name, defaultValueProvider?.invoke())
    }
}

