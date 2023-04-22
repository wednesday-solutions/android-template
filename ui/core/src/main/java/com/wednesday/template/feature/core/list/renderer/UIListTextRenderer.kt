package com.wednesday.template.presentation.base.list.renderer

import android.view.ViewGroup
import com.wednesday.template.feature.core.list.UIListText
import com.wednesday.template.resources.databinding.ItemListTextBinding

class UIListTextRenderer : ListItemRenderer<UIListText>() {

    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UIListText> {
        return UIListTextViewHolder(
            binding = viewGroup bind ItemListTextBinding::inflate
        )
    }
}
