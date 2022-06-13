package com.wednesday.template.presentation.weather.search

import com.wednesday.template.presentation.base.list.UIList
import com.wednesday.template.presentation.base.toolbar.UIToolbar
import com.wednesday.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchScreenState(
    override val toolbar: UIToolbar,
    override val showLoading: Boolean,
    val searchList: UIList = UIList()
) : MainScreenState
