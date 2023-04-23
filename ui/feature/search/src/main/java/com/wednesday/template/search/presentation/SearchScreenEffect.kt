package com.wednesday.template.search.presentation

import com.wednesday.template.feature.core.effect.Effect
import com.wednesday.template.feature.core.effect.SnackbarEffectData

sealed interface SearchScreenEffect: Effect {
    object NavigateBack: SearchScreenEffect

    data class ShowSnackbarEffect(
        val snackbarEffectData: SnackbarEffectData
    ): SearchScreenEffect
}