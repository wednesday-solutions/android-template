package com.wednesday.template.presentation.base.effect

import com.wednesday.template.presentation.datetime.UIDate

data class ShowDatePickerEffect(
    val selectedDate: UIDate,
    val minDate: UIDate? = null,
    val maxDate: UIDate? = null,
    val onDateSelected: (UIDate) -> Unit
) : Effect
