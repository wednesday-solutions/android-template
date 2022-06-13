package com.wednesday.template.presentation.base.list

interface BaseAdapter {

    val items: List<UIListItemBase>

    fun updateData(newItems: List<UIListItemBase>)
}
