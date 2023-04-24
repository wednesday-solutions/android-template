package com.wednesday.template.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.wednesday.template.data.core.UnitCallback
import com.wednesday.template.designSystem.composables.list.ListLoader
import com.wednesday.template.feature.core.composables.list.UILazyColumn
import com.wednesday.template.feature.core.composables.screen.ScreenConnector
import com.wednesday.template.feature.core.composables.screen.ScreenContainerData
import com.wednesday.template.feature.core.composables.screen.ScreenContent
import com.wednesday.template.feature.core.extensions.showSnackbar
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.search.presentation.list.UICityListItem
import org.koin.androidx.compose.getViewModel

@Composable
@Destination
fun SearchScreen(
    viewModel: SearchViewModel = getViewModel(),
    searchNavigator: SearchNavigator,
) {
    ScreenConnector(
        viewModel = viewModel,
        onEffect = {
            when (it) {
                SearchScreenEffect.NavigateBack -> searchNavigator.navigateBack()
                is SearchScreenEffect.ShowSnackbarEffect -> snackbarHostState.showSnackbar(it.snackbarEffectData)
            }
        },
    ) { state ->
        SearchScreenContent(
            onToolbarBackPressed = searchNavigator::navigateBack,
            onSearchTextChange = viewModel::search,
            onFavouriteClick = viewModel::onFavouriteClick,
            state = state
        )
    }
}


