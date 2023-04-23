package com.wednesday.template.home.presentation

import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeScreenState(
    override val toolbar: UIToolbar,
    override val showLoading: Boolean,
    val items: UIList
) : MainScreenState
