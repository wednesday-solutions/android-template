package com.wednesday.template.presentation.weather.home.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.presentation.base.list.renderer.ListItemRenderer
import com.wednesday.template.presentation.weather.UIWeather
import com.wednesday.template.presentation.weather.home.HomeScreenIntent

class UIWeatherRenderer : ListItemRenderer<UIWeather, HomeScreenIntent> {
    @Composable
    override fun render(modifier: Modifier, item: UIWeather, onIntent: (HomeScreenIntent) -> Unit) {
        TODO("Not yet implemented")
    }
}
