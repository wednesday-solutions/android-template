package com.wednesday.template.home.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wednesday.template.data.core.UnitCallback
import com.wednesday.template.designSystem.theme.AppTheme
import com.wednesday.template.feature.core.composables.list.UILazyColumn
import com.wednesday.template.feature.core.composables.screen.ScreenContainerData
import com.wednesday.template.feature.core.composables.screen.ScreenContainerData.Companion.screenContainerData
import com.wednesday.template.feature.core.composables.screen.ScreenContent
import com.wednesday.template.home.R
import com.wednesday.template.home.presentation.list.SearchCities
import com.wednesday.template.home.presentation.list.UIWeatherListItem
import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.weather.UISearchCitiesPlaceholder
import com.wednesday.template.presentation.weather.UIWeather

@Composable
fun ScreenContainerData.HomeScreenContent(state: HomeScreenState, navigateToSearch: UnitCallback) {
    ScreenContent(
        onToolbarBackPressed = { /* no-op */ },
        state = state,
        toolbarActions = {
            IconButton(onClick = navigateToSearch) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.acc_search)
                )
            }
        },
    ) {
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
                    onSearchClick = navigateToSearch,
                )

                else -> {}
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun HomeScreenContentPreview() {
    val screenContainerData = screenContainerData()
    val homeScreenState = HomeScreenState(
        toolbar = UIToolbar(title = UIText("Home"), hasBackButton = false),
        showLoading = false,
        items = UIList(
            UIWeather(
                lat = 10.324,
                lon = 20.53,
                title = UIText("Title"),
                description = UIText("This is description text"),
                currentTemp = UIText("30"),
                minMaxTemp = UIText("Min 28 with Max of 35"),
                feelsLike = UIText("Feels like 30"),
                iconUrl = "https://ssl.gstatic.com/onebox/weather/64/fog.png"
            ),
            UISearchCitiesPlaceholder(
                text = UIText("This placeholder is for when list is empty")
            )
        )
    )
    AppTheme {
        screenContainerData.HomeScreenContent(state = homeScreenState) {
            /* no-op */
        }
    }
}