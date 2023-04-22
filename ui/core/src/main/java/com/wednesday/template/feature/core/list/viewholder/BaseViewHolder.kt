package com.wednesday.template.presentation.base.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.intent.Intent
import kotlinx.coroutines.channels.Channel

abstract class BaseViewHolder<T : UIListItemBase>(
    viewBinding: ViewBinding
) : RecyclerView.ViewHolder(viewBinding.root), ViewHolderBinder<T> {

    lateinit var intentChannel: Channel<Intent>
    lateinit var item: T
    private var previousItem: T? = null

    abstract fun onSetupIntents(intentChannel: Channel<Intent>)

    override fun onBind(current: T) {
        if (::item.isInitialized) {
            previousItem = item
        }
        item = current
        onBindInternal()
        previousItem = null
    }

    protected abstract fun onBindInternal()

    protected fun <R> compareAndSet(operator: T.() -> R, action: (R) -> Unit) {
        val previousItem = previousItem
        val newResult = operator(item)
        previousItem ?: run {
            action(newResult)
            return
        }
        val previousResult = operator(previousItem)
        if (previousResult != newResult) {
            action(newResult)
        }
    }
}
