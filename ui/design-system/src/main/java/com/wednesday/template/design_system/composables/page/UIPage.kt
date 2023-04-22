package com.wednesday.template.design_system.composables.page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wednesday.template.design_system.composables.bars.UIToolbar

@Composable
fun UIPage(
    modifier: Modifier = Modifier,
    title: String,
    hasBackButton: Boolean,
    onBackPress: () -> Unit,
    backButtonContentDescription: String? = null,
    toolbarActions: @Composable RowScope.() -> Unit = {},
    toolbarModifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.then(
            Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
        ),
        topBar = {
            UIToolbar(
                modifier = toolbarModifier,
                title = title,
                hasBackButton = hasBackButton,
                onBackPress = onBackPress,
                actions = toolbarActions,
                backButtonContentDescription = backButtonContentDescription
            )
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}