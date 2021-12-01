package com.wednesday.template.presentation.weather.home

import com.wednesday.template.presentation.base.UIList
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeScreenState(
    override val toolbar: UIToolbar,
    override val showLoading: Boolean,
    val items: UIList
) : MainScreenState
