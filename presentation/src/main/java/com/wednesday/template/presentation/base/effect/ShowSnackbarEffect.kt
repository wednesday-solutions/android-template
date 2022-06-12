package com.wednesday.template.presentation.base.effect

import com.wednesday.template.presentation.base.text.UIText

data class ShowSnackbarEffect(
    val message: UIText,
    val action: UIText? = null,
    val onActionClick: () -> Unit = {},
    val onDismiss: () -> Unit = {}
) : Effect
