package com.wednesday.template.feature.core.list

import androidx.lifecycle.ViewModel
import com.wednesday.template.feature.core.R
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer

internal class ListComponent<T, I : Intent>(
    listViewModel: T,
    recyclerViewId: Int = 1,
    callback: ListComponent<T, I>.() -> Unit,
) : BaseListComponent<T, I>(
    listViewModel,
    recyclerViewId
) where T : ViewModel, T : IntentHandler<I> {

    init {
        callback(this)
    }

    @Suppress("UNCHECKED_CAST")
    internal inline fun <reified T : UIListItemBase> addRenderer(renderer: ListItemRenderer<T>) {
        renderers.add(T::class to (renderer as ListItemRenderer<UIListItemBase>))
    }
}
