package com.wednesday.template.presentation.base

import android.content.Context
import androidx.fragment.app.Fragment

fun Fragment.getString(text: UIText): String {
    return getString(text.resId)
}

fun UIText.asString(context: Context): String {
    return context.getString(resId)
}
