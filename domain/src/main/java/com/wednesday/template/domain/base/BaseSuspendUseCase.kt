package com.wednesday.template.domain.base

interface BaseSuspendUseCase<IN, OUT> {

    suspend operator fun invoke(param: IN): Result<OUT> {
        return try {
            Result.Success(invokeInternal(param))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun invokeInternal(param: IN): OUT
}
