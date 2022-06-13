package com.wednesday.template.presentation.base.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlin.reflect.KClass

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T : Intent, L : UIListItemBase, R : ListItemRenderer<L, T>> UILazyColumn(
    modifier: Modifier = Modifier.fillMaxSize(),
    renderers: Map<KClass<*>, R>,
    items: List<L>,
    onIntent: (T) -> Unit
) {
    val intentChannel = remember { Channel<T>(capacity = Channel.CONFLATED) }

    LaunchedEffect(key1 = intentChannel) {
        intentChannel.receiveAsFlow().collect(onIntent)
    }

    LazyColumn(modifier = modifier) {
        itemsIndexed(
            items = items,
            key = { _, item -> item.id }
        ) { _, item ->
            val type = item::class
            val renderer = renderers[type]
                ?: throw IllegalArgumentException(
                    "No renderer found for type $type." +
                            " Please add it to the renderers map."
                )

            renderer.render(
                modifier = Modifier.animateItemPlacement(),
                item = item,
                onIntent = intentChannel::trySend,
            )
        }
    }
}