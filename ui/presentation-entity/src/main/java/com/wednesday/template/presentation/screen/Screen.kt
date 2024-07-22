package com.wednesday.template.presentation.screen

import android.os.Parcelable
import androidx.annotation.IdRes

sealed class Screen(@IdRes val id: Int) : Parcelable

abstract class MainScreen(
    @IdRes id: Int,
) : Screen(id = id)
