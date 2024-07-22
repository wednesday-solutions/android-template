package com.wednesday.template.feature.core.composables.screen

import android.content.res.Configuration
import android.os.Parcel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wednesday.template.data.core.UnitCallback
import com.wednesday.template.designSystem.composables.page.UIPage
import com.wednesday.template.designSystem.composables.text.UITextType
import com.wednesday.template.designSystem.composables.text.UITextView
import com.wednesday.template.designSystem.theme.AppTheme
import com.wednesday.template.feature.core.R
import com.wednesday.template.feature.core.composables.screen.ScreenContainerData.Companion.screenContainerData
import com.wednesday.template.feature.core.extensions.asString
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.screen.MainScreenState

@Composable
fun <STATE : MainScreenState> ScreenContainerData.ScreenContent(
    onToolbarBackPressed: UnitCallback,
    backButtonContentDescription: String = stringResource(id = R.string.acc_navigate_back),
    toolbarActions: @Composable RowScope.() -> Unit = {},
    state: STATE,
    content: @Composable STATE.(PaddingValues) -> Unit,
) {
    UIPage(
        title = state.toolbar.title.asString(),
        snackbarHostState = snackbarHostState,
        hasBackButton = state.toolbar.hasBackButton,
        onBackPress = onToolbarBackPressed,
        backButtonContentDescription = backButtonContentDescription,
        toolbarActions = toolbarActions,
    ) { paddingValues ->
        state.content(paddingValues)
    }
}

@Preview("Screen Content", group = "Devices", device = "id:pixel_6_pro",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview("Screen Content", group = "Devices", device = "id:pixel",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview("Screen Content", group = "Devices",
    device = "spec:parent=6.7in Foldable,orientation=landscape"
)
@Composable
fun ScreenContentPreview() {
    val screenContainerData = screenContainerData()
    val previewState = object : MainScreenState {
        override val toolbar: UIToolbar
            get() = UIToolbar(
                title = UIText("Screen Title"),
                hasBackButton = true
            )
        override val showLoading: Boolean
            get() = false

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(p0: Parcel, p1: Int) {
            /* no-op */
        }

    }
    AppTheme {
        screenContainerData.ScreenContent(onToolbarBackPressed = { /*TODO*/ }, state = previewState) {
            Box(modifier = Modifier.padding(it).fillMaxSize()) {
                UITextView(modifier = Modifier.align(Alignment.Center), text = "Screen Content Preview", textType = UITextType.Medium.Bold)
            }
        }
    }
}