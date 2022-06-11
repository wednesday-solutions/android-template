package com.wednesday.template.presentation.base.extensions

import com.wednesday.template.presentation.base.text.UIText

fun String.asUIText(): UIText {
    return UIText {
        block(this@asUIText)
    }
}