package com.wednesday.template.domain.base

interface BaseUnsafeUseCase<IN, OUT> {

    operator fun invoke(param: IN): OUT
}