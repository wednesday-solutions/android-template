package com.wednesday.template.presentation.base.component

abstract class StatefulComponent<T> : Component<T>() {

    private var _currentData: T? = null
    val currentData: T?
        get() = _currentData

    protected abstract fun setDataInternal(newData: T)

    abstract fun unBindInternal()

    final override fun setData(data: T) {
        if (data !== _currentData) {
            setDataInternal(data)
        }
        _currentData = data
    }

    final override fun unbindInternal() {
        _currentData = null
        unBindInternal()
    }
}
