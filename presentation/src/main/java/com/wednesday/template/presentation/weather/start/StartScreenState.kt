package com.wednesday.template.presentation.weather.start

import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class StartScreenState(
    override val toolbar: UIToolbar,
    override val showLoading: Boolean
) : MainScreenState
