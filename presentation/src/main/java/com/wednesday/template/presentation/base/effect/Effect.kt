package com.wednesday.template.presentation.base.effect

import android.view.View
import com.wednesday.template.presentation.base.UIText
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
    val onActionClick: (View) -> Unit = {}
) : Effect

object HideKeyboardEffect : Effect
