package com.wednesday.template.domain.base.datetime

import com.wednesday.template.domain.date.Date
import com.wednesday.template.repo.date.DateRepo
import timber.log.Timber

class ConvertDateToLongUseCaseImpl(
    private val dateRepo: DateRepo
): ConvertDateToLongUseCase {

    override fun invoke(param: Date): Long {
        Timber.tag(TAG).d("invoke: param = $param")
        return dateRepo.convertToLong(param)
    }

    companion object {
        private const val TAG = "ConvertDateToLongUseCaseImpl"
    }
}