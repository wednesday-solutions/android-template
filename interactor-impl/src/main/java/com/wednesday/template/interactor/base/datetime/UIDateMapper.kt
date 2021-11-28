package com.wednesday.template.interactor.base.datetime

import com.wednesday.template.domain.base.datetime.ConvertDateToLongUseCase
import com.wednesday.template.domain.date.Date
import com.wednesday.template.interactor.base.Mapper
import com.wednesday.template.presentation.datetime.UIDate
import timber.log.Timber

interface UIDateMapper : Mapper<Date, UIDate>

class UIDateMapperImpl(
    private val convertDateToLongUseCase: ConvertDateToLongUseCase
) : UIDateMapper {

    override fun map(from: Date): UIDate {
        Timber.tag(TAG).d("map: from = $from")
        return UIDate(
            dayOfMonth = from.dayOfMonth,
            month = from.month,
            year = from.year,
            timeAsLong = convertDateToLongUseCase(from)
        )
    }

    companion object {
        private const val TAG = "UIDateMapperImpl"
    }
}
