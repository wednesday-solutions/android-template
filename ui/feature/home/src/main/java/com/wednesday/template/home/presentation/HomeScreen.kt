package com.wednesday.template.home.presentation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.wednesday.template.feature.core.composables.screen.ScreenConnector
import com.wednesday.template.feature.core.extensions.showSnackbar
import org.koin.androidx.compose.getViewModel

@Composable
@Destination
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navigator: HomeNavigator,
) {
    ScreenConnector(
        viewModel = viewModel,
        onEffect = {
            when (it) {
                is HomeScreenEffect.ShowSnackbarEffect -> snackbarHostState.showSnackbar(it.data)
                HomeScreenEffect.NavigateToSearch -> navigator.navigateToSearch()
            }
        },
    ) { state ->
        HomeScreenContent(state = state) {
            navigator.navigateToSearch()
        }
    }
}
