package com.wednesday.template.presentation.screen

import com.wednesday.template.presentation.base.UIToolbar

interface MainScreenState : ScreenState {
    val toolbar: UIToolbar
    val showLoading: Boolean
}
