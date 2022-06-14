package com.wednesday.template.presentation.weather.home.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.presentation.weather.home.HomeScreenIntent

class UISearchCitiesPlaceholderRenderer : ListItemRenderer<UISearchCitiesPlaceholder, HomeScreenIntent> {

    @Composable
    override fun render(
        modifier: Modifier,
        item: UISearchCitiesPlaceholder,
        onIntent: (HomeScreenIntent) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}
