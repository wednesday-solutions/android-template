package com.wednesday.template.domain.weather

import com.wednesday.template.domain.base.BaseSuspendUseCase

interface SearchCitiesUseCase: BaseSuspendUseCase<String, List<City>>