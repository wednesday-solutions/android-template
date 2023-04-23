package com.wednesday.template.feature.core.composables.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIListItemBase

typealias UILazyColumnItemComposable = @Composable LazyItemScope.(modifier: Modifier, item: UIListItemBase) -> Unit

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UILazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    items: UIList,
    itemComposable: UILazyColumnItemComposable
) {

    LazyColumn(modifier = modifier, state = state) {
        itemsIndexed(
            items = items.items,
            key = { _, item -> item.id }
        ) { _, item ->
            val type = item::class

            itemComposable(
                modifier = Modifier.animateItemPlacement(),
                item = item,
            )
        }
    }
}