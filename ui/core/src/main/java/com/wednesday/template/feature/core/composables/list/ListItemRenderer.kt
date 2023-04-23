package com.wednesday.template.feature.core.composables.list

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.intent.Intent

interface ListItemRenderer<T : UIListItemBase, I : Intent> {

    @Composable
    fun LazyItemScope.Render(modifier: Modifier, item: T, onIntent: (I) -> Unit)
}