package com.wednesday.template.domain.base

import kotlinx.coroutines.flow.Flow

interface BaseFlowUseCase<IN, OUT> {

    operator fun invoke(param: IN): Flow<OUT> = invokeInternal(param)

    fun invokeInternal(param: IN): Flow<OUT>
}