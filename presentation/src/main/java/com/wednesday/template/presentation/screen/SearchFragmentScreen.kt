package com.wednesday.template.presentation.screen

import com.wednesday.template.presentation_entity.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchFragmentScreen(
    var hello: String = ""
):MainScreen(R.id.searchFragment)