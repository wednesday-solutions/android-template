package com.wednesday.template.domain.base.datetime

import com.wednesday.template.domain.date.Date
import com.wednesday.template.repo.date.DateRepo
import timber.log.Timber

class FormatDateUseCaseImpl(
    private val dateRepo: DateRepo
) : FormatDateUseCase {

    override fun invoke(param: Pair<Date, String>): String {
        Timber.tag(TAG).d("invoke: param = $param")
        return dateRepo.format(param.first, param.second)
    }

    companion object {
        private const val TAG = "FormatDateUseCaseImpl"
    }
}
