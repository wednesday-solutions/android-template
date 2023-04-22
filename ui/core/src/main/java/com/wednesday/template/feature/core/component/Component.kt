package com.wednesday.template.presentation.base.component

import android.view.View

abstract class Component<T> {

    protected var view: View? = null

    protected abstract fun bindInternal(view: View)

    abstract fun setData(data: T)

    protected abstract fun unbindInternal()

    fun bind(view: View) {
        this.view = view
        bindInternal(view)
    }

    fun unbind() {
        this.view = null
        unbindInternal()
    }
}
