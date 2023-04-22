package com.wednesday.template.presentation.base.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wednesday.template.presentation.base.component.Component
import com.wednesday.template.presentation.base.effect.HideKeyboardEffect

class HideKeyboardComponent(
    private val activity: Activity?
) : Component<HideKeyboardEffect>() {

    override fun bindInternal(view: View) = Unit

    override fun setData(data: HideKeyboardEffect) {
        val activity = activity ?: return
        val view = view ?: return
        val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun unbindInternal() = Unit
}
