package com.wednesday.template.presentation.base.list.viewholder

import com.wednesday.template.presentation.base.list.UIListItemBase

interface ViewHolderBinder<T : UIListItemBase> {

    fun onBind(current: T)
}
