package com.wednesday.template.domain.base

sealed class Result<out T> {

    class Success<T>(val data: T) : Result<T>()

    class Error(val exception: Exception) : Result<Nothing>()
}