package com.wednesday.template.home.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wednesday.template.feature.core.composables.list.ListItemRenderer
import com.wednesday.template.feature.core.composables.list.UILazyColumn
import com.wednesday.template.feature.core.composables.screen.Screen
import com.wednesday.template.presentation.weather.UICity
import org.koin.androidx.compose.getViewModel

@RootNavGraph(start = true)
@Composable
@Destination
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navigator: DestinationsNavigator,
) {
    Screen(
        viewModel = viewModel,
        onBackPress = navigator::navigateUp,
        onEffect = {
            when (it) {
                is HomeScreenEffect.ShowSnackbarEffect -> TODO()
            }
        },
    ) {
        UILazyColumn(
            modifier = Modifier.padding(it),
            renderers = mapOf(),
            items = items.items,
            onIntent = viewModel::onIntent
        )
    }
}