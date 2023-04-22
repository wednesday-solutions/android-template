package com.wednesday.template.feature.core.extensions

import android.content.Context
import com.wednesday.template.presentation.base.Raw
import com.wednesday.template.presentation.base.RawFormatted
import com.wednesday.template.presentation.base.Resource
import com.wednesday.template.presentation.base.ResourceFormatted
import com.wednesday.template.presentation.base.UIText
import org.koin.core.component.KoinComponent

internal object KoinComponentWrapper : KoinComponent

internal fun UIText.asString(): String {
    val lazyContext = lazy {
        KoinComponentWrapper.getKoin().get<Context>()
    }
    return blocks.fold("") { acc, next -> acc + next.string(lazyContext) }
}

private fun UIText.Block.string(lazyContext: Lazy<Context>) = when (this) {
    is Raw -> text
    is RawFormatted -> text.format(arg1, arg2, arg3)
    is Resource -> resId.asString(lazyContext)
    is ResourceFormatted -> resId.asString(lazyContext).format(arg1, arg2, arg3)
    else -> throw IllegalStateException("Disallowed block")
}

private fun Int.asString(lazyContext: Lazy<Context>): String {
    return lazyContext.value.getString(this)
}
