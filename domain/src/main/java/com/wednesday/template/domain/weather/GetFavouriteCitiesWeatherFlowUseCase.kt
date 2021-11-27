package com.wednesday.template.domain.weather

import com.wednesday.template.domain.base.BaseFlowUseCase

interface GetFavouriteCitiesWeatherFlowUseCase: BaseFlowUseCase<Unit, List<Weather>>