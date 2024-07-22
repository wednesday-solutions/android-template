package com.wednesday.template.feature.core.state

import androidx.lifecycle.SavedStateHandle

interface StateOwner {

    val savedStateHandle: SavedStateHandle
}
