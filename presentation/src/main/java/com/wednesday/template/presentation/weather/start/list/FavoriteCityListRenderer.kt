package com.wednesday.template.presentation.weather.start.list

import android.view.ViewGroup
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.FavoriteCity
import com.wednesday.template.resources.databinding.FavoriteCityItemListBinding
import com.wednesday.template.resources.databinding.FragmentStartBinding

class FavoriteCityListRenderer: ListItemRenderer<FavoriteCity>() {
    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<FavoriteCity> {
        return FavoriteCityListViewHolder(
            binding = viewGroup bind FavoriteCityItemListBinding::inflate
        )
    }
}