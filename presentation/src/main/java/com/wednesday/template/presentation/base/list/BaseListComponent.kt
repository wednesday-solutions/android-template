package com.wednesday.template.presentation.base.list

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wednesday.template.presentation.R
import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.component.StatefulComponent
import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.base.intent.IntentHandler
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

internal open class BaseListComponent<T, I : Intent>(
    protected val listViewModel: T,
    private val recyclerViewId: Int = R.id.recyclerView,
) : StatefulComponent<UIList>() where T : ViewModel, T : IntentHandler<I> {

    protected val renderers = mutableListOf<Pair<KClass<*>, ListItemRenderer<UIListItemBase>>>()

    private val intentChannel = Channel<I>(Channel.CONFLATED)

    protected val recyclerView: RecyclerView
        get() = requireNotNull(view?.findViewById(recyclerViewId)) {
            "View bound to this component must contain RecyclerView with id `recyclerView`"
        }

    private var actionsJob: Job? = null
    private var diffJob: Job? = null

    override fun bindInternal(view: View) {
        initRecyclerView()
        actionsJob?.cancel()
        actionsJob = listViewModel.viewModelScope.launch {
            intentChannel
                .receiveAsFlow()
                .filterNotNull()
                .collect {
                    listViewModel.onIntent(it)
                }
        }
    }

    override fun setDataInternal(newData: UIList) = recyclerView.adapter?.run {
        // Fast path 1: no list in adapter before, just update
        this as BaseAdapter

        if (items.isEmpty()) {
            updateData(newData.items)
            notifyDataSetChanged()
            return@run
        }

        // Slow path: use diffUtil to find differences between old and new lists
        diffJob?.cancel()
        diffJob = listViewModel.viewModelScope.launch(Dispatchers.Default) {
            val diff = DiffUtil.calculateDiff(
                ListDiffCallback(
                    oldList = items,
                    newList = newData.items
                ),
                true
            )
            withContext(Dispatchers.Main) {
                val recyclerViewState = recyclerView.layoutManager?.onSaveInstanceState()
                updateData(newData.items)
                recyclerViewState?.let {
                    recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
                }
                diff.dispatchUpdatesTo(this@run)
            }
        }
    } ?: Unit

    override fun unBindInternal() {
        actionsJob?.cancel()
        actionsJob = null
        diffJob?.cancel()
        diffJob = null
    }

    @Suppress("UNCHECKED_CAST")
    private fun initRecyclerView() {
        val view = view ?: return
        recyclerView.run {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ListAdapter(
                intentChannel as Channel<Intent>,
                renderers
            )
        }
    }
}
