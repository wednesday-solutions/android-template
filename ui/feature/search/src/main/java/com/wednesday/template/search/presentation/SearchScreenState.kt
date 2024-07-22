package com.wednesday.template.search.presentation

import com.wednesday.template.presentation.UIList
import com.wednesday.template.presentation.base.UIToolbar
import com.wednesday.template.presentation.screen.MainScreenState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchScreenState(
    override val toolbar: UIToolbar,
    override val showLoading: Boolean,
    val searchText: String = "",
    val searchList: UIList = UIList(),
) : MainScreenState
