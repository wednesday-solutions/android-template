package com.wednesday.template.presentation.weather.search

import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchFragmentScreenState(
    override val toolbar: UIToolbar,
    override val showLoading: Boolean
) :MainScreenState
