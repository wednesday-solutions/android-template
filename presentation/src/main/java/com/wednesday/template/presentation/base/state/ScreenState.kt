package com.wednesday.template.presentation.base.state

import android.os.Parcelable
import com.wednesday.template.presentation.base.UIToolbar

interface ScreenState : Parcelable

interface MainScreenState : ScreenState {
    val toolbar: UIToolbar
    val showLoading: Boolean
}
