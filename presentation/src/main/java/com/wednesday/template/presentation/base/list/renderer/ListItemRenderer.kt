package com.wednesday.template.presentation.base.list.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.viewbinding.ViewBinding
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.UIListItemBase
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder

typealias Inflater<T> = (inflater: LayoutInflater, viewGroup: ViewGroup, attachToParent: Boolean) -> T

abstract class ListItemRenderer<T : UIListItemBase, I : Intent> : ItemRenderer<T, I> {

    @Composable
    open fun render(modifier: Modifier, item: T, onIntent: (I) -> Unit) {}

    @Deprecated("Use render(item: T, onIntent: (Intent) -> Unit) instead after moving to compose.")
    abstract fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<T>

    protected infix fun <VB : ViewBinding> ViewGroup.bind(inflater: Inflater<VB>): VB {
        val layoutInflater = LayoutInflater.from(context)
        return inflater(layoutInflater, this, false)
    }
}
