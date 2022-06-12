package com.wednesday.template.presentation.base.effect

import androidx.compose.ui.graphics.vector.ImageVector
import com.wednesday.template.presentation.base.dialog.DialogHostState
import com.wednesday.template.presentation.base.text.UIText

data class ShowAlertDialogEffect(
    val icon: ImageVector? = null,
    val iconContentDescription: String? = null,
    val title: UIText,
    val text: UIText,
    val confirmButtonText: UIText,
    val onConfirmButtonClicked: (DialogHostState) -> Unit = {},
    val dismissButtonText: UIText? = null,
    val onDismissButtonClicked: (DialogHostState) -> Unit = {},
) : Effect