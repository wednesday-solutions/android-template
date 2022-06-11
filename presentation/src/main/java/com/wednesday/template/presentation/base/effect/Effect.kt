package com.wednesday.template.presentation.base.effect

import androidx.compose.ui.graphics.vector.ImageVector
import com.wednesday.template.presentation.base.dialog.DialogHostState
import com.wednesday.template.presentation.base.text.UIText
import com.wednesday.template.presentation.datetime.UIDate

interface Effect

data class ShowDatePickerEffect(
    val selectedDate: UIDate,
    val minDate: UIDate? = null,
    val maxDate: UIDate? = null,
    val onDateSelected: (UIDate) -> Unit
) : Effect

data class ShowSnackbarEffect(
    val message: UIText,
    val action: UIText? = null,
    val onActionClick: () -> Unit = {},
    val onDismiss: () -> Unit = {}
) : Effect

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

object HideKeyboardEffect : Effect
