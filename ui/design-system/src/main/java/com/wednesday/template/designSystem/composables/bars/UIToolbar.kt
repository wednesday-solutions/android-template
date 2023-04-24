package com.wednesday.template.designSystem.composables.bars

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UIToolbar(
    modifier: Modifier = Modifier,
    title: String,
    hasBackButton: Boolean,
    onBackPress: () -> Unit,
    backButtonContentDescription: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(title)
        },
        navigationIcon = {
            if (hasBackButton) {
                IconButton(onClick = onBackPress) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = backButtonContentDescription,
                    )
                }
            }
        },
        actions = actions,
    )
}
