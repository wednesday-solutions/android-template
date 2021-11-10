package com.wednesday.template.presentation.base.list

import com.wednesday.template.presentation.base.list.UIListItemBase

interface BaseAdapter {

    val items: List<UIListItemBase>

    fun updateData(newItems: List<UIListItemBase>)
}
