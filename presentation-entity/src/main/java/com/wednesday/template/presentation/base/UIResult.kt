package com.wednesday.template.presentation.base

sealed class UIResult<out T> {

    data class Success<T>(val data: T) : UIResult<T>()

    data class Error(val exception: Exception) : UIResult<Nothing>()
}
