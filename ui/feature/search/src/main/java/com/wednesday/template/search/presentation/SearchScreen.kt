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
import com.wednesday.template.design_system.composables.list.ListLoader
import com.wednesday.template.feature.core.composables.list.UILazyColumn
import com.wednesday.template.feature.core.composables.screen.Screen
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
    Screen(
        viewModel = viewModel, onEffect = {
            when (it) {
                SearchScreenEffect.NavigateBack -> searchNavigator.navigateBack()
                is SearchScreenEffect.ShowSnackbarEffect -> snackbarHostState.showSnackbar(it.snackbarEffectData)
            }
        }, onToolbarBackPressed = searchNavigator::navigateBack
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = 16.dp)) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = viewModel::search
            )

            Spacer(Modifier.height(16.dp))

            if (showLoading) {
                ListLoader()
            } else {
                UILazyColumn(items = searchList) { modifier, item ->
                    when (item) {
                        is UICity -> UICityListItem(
                            modifier,
                            item,
                            onFavouriteClick = viewModel::onFavouriteClick
                        )
                    }
                }
            }
        }
    }
}
