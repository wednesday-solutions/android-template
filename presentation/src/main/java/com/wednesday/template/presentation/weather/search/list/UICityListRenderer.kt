package com.wednesday.template.presentation.weather.search.list

import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.base.list.viewholder.BaseViewHolder
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.presentation.weather.home.HomeScreenIntent
import com.wednesday.template.resources.databinding.CityItemListBinding

class UICityListRenderer : ListItemRenderer<UICity, HomeScreenIntent>() {
    override fun getViewHolder(viewGroup: ViewGroup): BaseViewHolder<UICity> {
        return UICityListViewHolder(
            binding = viewGroup bind CityItemListBinding::inflate
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun render(modifier: Modifier, item: UICity, onIntent: (HomeScreenIntent) -> Unit) {
        Card(modifier = modifier
            .clickable {
                onIntent(HomeScreenIntent.Loading)
            }
            .fillMaxWidth()
            .padding(32.dp)) {
            Text(text = item.title)
        }
    }
}
