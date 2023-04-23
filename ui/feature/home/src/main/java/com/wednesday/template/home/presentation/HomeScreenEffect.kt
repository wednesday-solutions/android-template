package com.wednesday.template.home.presentation

import com.wednesday.template.feature.core.effect.Effect
import com.wednesday.template.feature.core.effect.SnackbarEffectData

sealed interface HomeScreenEffect : Effect {
    data class ShowSnackbarEffect(
        val data: SnackbarEffectData,
    ) : HomeScreenEffect

    object NavigateToSearch: HomeScreenEffect
}