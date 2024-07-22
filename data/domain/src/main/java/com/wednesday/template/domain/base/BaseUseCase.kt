package com.wednesday.template.domain.base

import timber.log.Timber

interface BaseUseCase<IN, OUT> {

    operator fun invoke(param: IN): Result<OUT> {
        return try {
            Result.Success(invokeInternal(param))
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(e)
        }
    }

    fun invokeInternal(param: IN): OUT
}
