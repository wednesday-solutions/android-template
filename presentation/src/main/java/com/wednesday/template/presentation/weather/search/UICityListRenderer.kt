package com.wednesday.template.presentation.weather.search

import android.view.ViewGroup
import com.wednesday.template.presentation.base.list.UIListText
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.resources.databinding.CityItemListBinding


class UICityListRenderer : ListItemRenderer<UIListText>() {
    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIListText> {
        return UICityListViewHolder(
            binding = viewGroup bind CityItemListBinding::inflate
        )
    }
}