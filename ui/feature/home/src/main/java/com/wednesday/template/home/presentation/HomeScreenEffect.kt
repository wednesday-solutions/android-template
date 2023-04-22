package com.wednesday.template.home.presentation

import com.wednesday.template.feature.core.effect.Effect
import com.wednesday.template.presentation.base.UIText

sealed interface HomeScreenEffect : Effect {
    data class ShowSnackbarEffect(
        val message: UIText,
    ) : HomeScreenEffect
}