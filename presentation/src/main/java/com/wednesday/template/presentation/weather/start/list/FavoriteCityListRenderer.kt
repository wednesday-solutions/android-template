package com.wednesday.template.presentation.weather.start.list

import android.view.ViewGroup
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UIFavoriteCity
import com.wednesday.template.resources.databinding.FavoriteCityItemListBinding

class FavoriteCityListRenderer : ListItemRenderer<UIFavoriteCity>() {
    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIFavoriteCity> {
        return FavoriteCityListViewHolder(
            binding = viewGroup bind FavoriteCityItemListBinding::inflate
        )
    }
}
