package com.wednesday.template.interactor.base

import com.wednesday.template.domain.base.Result
import com.wednesday.template.presentation.base.result.UIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, S> Flow<Result<T>>.mapToUIResult(
    success: Result.Success<T>.() -> S,
    failure: (Result.Error.() -> UIResult.Error)? = null
): Flow<UIResult<S>> =
    map {
        when (it) {
            is Result.Success -> UIResult.Success(success(it))
            is Result.Error -> {
                if (failure != null) {
                    failure(it)
                } else {
                    UIResult.Error(it.exception)
                }
            }
        }
    }
