package com.wednesday.template.interactor.base

import com.wednesday.template.domain.base.Result
import com.wednesday.template.presentation.base.UIResult

abstract class BaseInteractor {

    fun <T> mapResult(
        from: Result<T>
    ): UIResult<T> = when (from) {
        is Result.Success -> UIResult.Success(from.data)
        is Result.Error -> UIResult.Error(from.exception)
    }

    fun <FROM, TO> mapResult(
        from: Result<FROM>,
        mapper: Mapper<FROM, TO>
    ): UIResult<TO> = when (from) {
        is Result.Success -> UIResult.Success(mapper.map(from.data))
        is Result.Error -> UIResult.Error(from.exception)
    }
}