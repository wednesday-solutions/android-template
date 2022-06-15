package com.wednesday.template.presentation.base.list.renderer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.UIListItemBase

interface ListItemRenderer<T : UIListItemBase, I : Intent> : ItemRenderer<T, I> {

    @Composable
    fun render(modifier: Modifier, item: T, onIntent: (I) -> Unit)
}
