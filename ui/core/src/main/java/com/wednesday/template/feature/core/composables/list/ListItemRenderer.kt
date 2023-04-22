package com.wednesday.template.feature.core.composables.list

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.UIListItemBase

interface ListItemRenderer<in T : UIListItemBase, I : Intent> {

    @Composable
    fun Render(modifier: Modifier, item: T, onIntent: (I) -> Unit)
}