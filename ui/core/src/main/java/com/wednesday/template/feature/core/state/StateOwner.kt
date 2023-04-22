package com.wednesday.template.presentation.base.state

import androidx.lifecycle.SavedStateHandle

interface StateOwner {

    val savedStateHandle: SavedStateHandle
}
