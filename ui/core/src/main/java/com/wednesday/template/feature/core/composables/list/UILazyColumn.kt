package com.wednesday.template.feature.core.composables.list

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.UIListItemBase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T : Intent, L : UIListItemBase, R : ListItemRenderer<L, T>> UILazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    renderers: Map<KClass<*>, R>,
    items: List<UIListItemBase>,
    onIntent: (T) -> Unit
) {
    val intentChannel = remember { Channel<T>(capacity = Channel.CONFLATED) }
    val internalRenderers = renderers as Map<KClass<*>, ListItemRenderer<UIListItemBase, T>>

    LaunchedEffect(key1 = intentChannel) {
        intentChannel.consumeAsFlow().collect(onIntent)
    }

    LazyColumn(modifier = modifier, state = state) {
        itemsIndexed(
            items = items,
            key = { _, item -> item.id }
        ) { _, item ->
            val type = item::class
            val renderer = internalRenderers[type]
                ?: error(
                    "No renderer found for type $type." +
                        " Please add it to the renderers map."
                )

            renderer.Render(
                modifier = Modifier.animateItemPlacement(),
                item = item,
                onIntent = intentChannel::trySend,
            )
        }
    }
}