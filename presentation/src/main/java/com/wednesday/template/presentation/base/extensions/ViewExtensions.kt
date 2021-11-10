package com.wednesday.template.presentation.base.extensions

import android.view.View

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.GONE
}

internal fun View.invisible() {
    visibility = View.INVISIBLE
}

internal fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
