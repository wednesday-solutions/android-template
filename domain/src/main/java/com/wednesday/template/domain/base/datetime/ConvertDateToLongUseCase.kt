package com.wednesday.template.domain.base.datetime

import com.wednesday.template.domain.base.BaseUnsafeUseCase
import com.wednesday.template.domain.date.Date

interface ConvertDateToLongUseCase: BaseUnsafeUseCase<Date, Long>