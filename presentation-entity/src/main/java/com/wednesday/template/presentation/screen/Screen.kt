package com.wednesday.template.presentation.screen

import android.os.Parcelable
import androidx.annotation.IdRes
import com.wednesday.template.presentation.routes.Routes
import kotlinx.parcelize.Parcelize

sealed class Screen(val route: String) : Parcelable

@Parcelize
object HomeScreen : Screen(Routes.HOME)

@Parcelize
object SearchScreen : Screen(Routes.SEARCH)


