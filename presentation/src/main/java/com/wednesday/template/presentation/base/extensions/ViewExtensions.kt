package com.wednesday.template.presentation.base.extensions

import android.view.View
import android.widget.TextView
import com.wednesday.template.presentation.base.UIText
import kudosfinance.android.kudosui.internal.entity.common.UIText
import kudosfinance.android.kudosui.internal.entity.common.asString

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

internal fun TextView.setUIText(uiText: UIText) {
    text = uiText.asString(context)
}
