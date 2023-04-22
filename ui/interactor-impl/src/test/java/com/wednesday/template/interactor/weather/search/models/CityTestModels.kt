package com.wednesday.template.interactor.weather.search.models

import com.wednesday.template.domain.weather.City
import com.wednesday.template.presentation.base.UIText
import com.wednesday.template.presentation.weather.UICity

val city = City(
    id = 1,
    title = "title 1",
    country = "location 1",
    lat = 10.10,
    lon = 30.55,
    state = "state 1"
)

val uiCity = UICity(
    cityId = city.id,
    title = city.title,
    displayTitle = UIText { block(city.title) },
    locationType = city.country,
    displayLocationType = UIText { block(city.country) },
    latitude = "${city.lat} ${city.lon}",
    isFavourite = false,
    state = city.state
)
