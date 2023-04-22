package com.wednesday.template.home.presentation.list

import android.view.ViewGroup
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.resources.databinding.ItemSearchCityPlaceholderBinding

class UISearchCitiesPlaceholderRenderer : ListItemRenderer<UISearchCitiesPlaceholder>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UISearchCitiesPlaceholder> {
        return UISearchCitiesPlaceholderViewHolder(
            binding = viewGroup bind ItemSearchCityPlaceholderBinding::inflate
        )
    }
}
