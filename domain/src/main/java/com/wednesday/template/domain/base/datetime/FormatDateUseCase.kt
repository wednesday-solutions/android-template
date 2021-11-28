package com.wednesday.template.domain.base.datetime

import com.wednesday.template.domain.base.BaseUnsafeUseCase
import com.wednesday.template.domain.date.Date

interface FormatDateUseCase : BaseUnsafeUseCase<Pair<Date, String>, String>
