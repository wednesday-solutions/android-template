package com.wednesday.template.search.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wednesday.template.data.core.UnitCallback
import com.wednesday.template.designSystem.composables.list.ListLoader
import com.wednesday.template.designSystem.composables.text.UITextType
import com.wednesday.template.designSystem.composables.text.UITextView
import com.wednesday.template.designSystem.theme.AppTheme
import com.wednesday.template.feature.core.composables.list.UILazyColumn
import com.wednesday.template.feature.core.composables.screen.ScreenContainerData
import com.wednesday.template.feature.core.composables.screen.ScreenContainerData.Companion.screenContainerData
import com.wednesday.template.feature.core.composables.screen.ScreenContent
import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.weather.UICity
import com.wednesday.template.search.R
import com.wednesday.template.search.presentation.list.UICityListItem

@Composable
fun ScreenContainerData.SearchScreenContent(
    onToolbarBackPressed: UnitCallback,
    onSearchTextChange: (String) -> Unit,
    onFavouriteClick: (UICity) -> Unit,
    state: SearchScreenState,
) {
    ScreenContent(
        onToolbarBackPressed = onToolbarBackPressed,
        state = state,
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp),
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = onSearchTextChange,
                placeholder = {
                    UITextView(
                        text = stringResource(id = R.string.enter_city_name),
                        textType = UITextType.Small.Regular,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.75f)
                    )
                }
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
                            onFavouriteClick = onFavouriteClick,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SearchScreenPreview() {
    val screenContainerData = screenContainerData()
    val searchScreenState = SearchScreenState(
        toolbar = UIToolbar(
            title = UIText("Search"), hasBackButton = true
        ),
        showLoading = false,
        searchText = "Mumbai",
        searchList = UIList(
            UICity(
                cityId = 1,
                title = "Mumbai",
                state = null,
                displayTitle = UIText("Mumbai"),
                locationType = "",
                displayLocationType = UIText("Location Type"),
                latitude = "10.3423, 32.4343",
                isFavourite = false
            ), UICity(
                cityId = 2,
                title = "Pune",
                state = null,
                displayTitle = UIText("Pune"),
                locationType = "",
                displayLocationType = UIText("Location Type"),
                latitude = "109.22, 66.43",
                isFavourite = true
            )
        ),
    )

    AppTheme {
        screenContainerData.SearchScreenContent(
            onToolbarBackPressed = { /*TODO*/ },
            onSearchTextChange = {},
            onFavouriteClick = {},
            state = searchScreenState
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SearchScreenLoadingPreview() {
    val screenContainerData = screenContainerData()
    val searchScreenState = SearchScreenState(
        toolbar = UIToolbar(
            title = UIText("Search"), hasBackButton = true
        ),
        showLoading = true,
        searchText = "",
        searchList = UIList(),
    )

    AppTheme {
        screenContainerData.SearchScreenContent(
            onToolbarBackPressed = { /*TODO*/ },
            onSearchTextChange = {},
            onFavouriteClick = {},
            state = searchScreenState
        )
    }
}