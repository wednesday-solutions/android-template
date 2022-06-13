package com.wednesday.template.presentation.weather.home.models

import com.wednesday.template.presentation.base.text.UIText
import com.wednesday.template.presentation.weather.UICity

val city = UICity(
    cityId = 1,
    title = "title 1",
    displayTitle = UIText { block("title 1") },
    locationType = "location 1",
    displayLocationType = UIText { block("location 1") },
    latitude = "latitude 1",
    isFavourite = true,
    state = "state 1"
)
