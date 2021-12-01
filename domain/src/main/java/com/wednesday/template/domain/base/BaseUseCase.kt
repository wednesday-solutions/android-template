package com.wednesday.template.domain.base

interface BaseUseCase<IN, OUT> {

    operator fun invoke(param: IN): Result<OUT> {
        return try {
            Result.Success(invokeInternal(param))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun invokeInternal(param: IN): OUT
}
