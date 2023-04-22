package com.wednesday.template.presentation.base.list.viewholder

import com.wednesday.template.presentation.base.UIListItemBase

interface ViewHolderBinder<T : UIListItemBase> {

    fun onBind(current: T)
}
