package com.wednesday.template.presentation.base

import android.content.Context

fun UIText.asString(): String {
    return blocks.fold("") { acc, next -> acc + next.string() }
}

fun UIText.asString(context: Context): String {
    return blocks.fold("") { acc, next -> acc + next.string(context) }
}

private fun UIText.Block.string() = when (this) {
    is Raw -> text
    is RawFormatted -> text.format(arg1, arg2, arg3)
    else -> throw IllegalStateException("Disallowed block")
}

private fun UIText.Block.string(context: Context) = when (this) {
    is Raw -> text
    is RawFormatted -> text.format(arg1, arg2, arg3)
    is Resource -> resId.asString(context)
    is ResourceFormatted -> resId.asString(context).format(arg1, arg2, arg3)
    else -> throw IllegalStateException("Disallowed block")
}

private fun Int.asString(context: Context): String {
    return context.getString(this)
}
