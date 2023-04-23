package com.wednesday.template.home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.wednesday.template.feature.core.composables.list.UILazyColumn
import com.wednesday.template.feature.core.composables.screen.Screen
import com.wednesday.template.feature.core.extensions.showSnackbar
import com.wednesday.template.home.R
import com.wednesday.template.home.presentation.list.SearchCities
import com.wednesday.template.home.presentation.list.UIWeatherListItem
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.presentation.weather.UIWeather
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
@Destination
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navigator: HomeNavigator,
) {
    Screen(
        viewModel = viewModel,
        onToolbarBackPressed = { /* no-op */ },
        onEffect = {
            when (it) {
                is HomeScreenEffect.ShowSnackbarEffect -> snackbarHostState.showSnackbar(it.data)
                HomeScreenEffect.NavigateToSearch -> navigator.navigateToSearch()
            }
        },
        toolbarActions = {
            IconButton(onClick = viewModel::navigateToSearch) {
                Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.acc_search))
            }
        }
    ) {
        Timber.e("$this")
        UILazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            items = items,
        ) { modifier: Modifier, item: UIListItemBase ->
            when (item) {
                is UIWeather -> UIWeatherListItem(modifier = modifier, item = item)
                is UISearchCitiesPlaceholder -> SearchCities(
                    modifier = modifier,
                    item = item,
                    onSearchClick = viewModel::navigateToSearch
                )
            }
        }
    }
}