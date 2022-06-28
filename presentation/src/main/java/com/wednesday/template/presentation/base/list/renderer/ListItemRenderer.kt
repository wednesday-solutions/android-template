package com.wednesday.template.presentation.base.list.renderer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.UIListItemBase

interface ListItemRenderer<in T : UIListItemBase, I : Intent> {

    @Composable
    fun render(modifier: Modifier, item: T, onIntent: (I) -> Unit)
}
