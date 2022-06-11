package com.wednesday.template.presentation.screen

import com.wednesday.template.presentation.base.toolbar.UIToolbar

interface MainScreenState : ScreenState {
    val toolbar: UIToolbar
    val showLoading: Boolean
}
