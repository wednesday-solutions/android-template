package com.wednesday.template.repo.weather.models

import com.wednesday.template.domain.weather.City

val cityMappedFromLocalCity = City(
    id = (localCity.lat + localCity.lon).toInt(),
    title = localCity.name,
    country = localCity.country,
    lat = localCity.lat,
    lon = localCity.lon,
    state = localCity.state,
)

val cityMappedFromRemoteCity = City(
    id = (localCity.lat + localCity.lon).toInt(),
    title = localCity.name,
    country = localCity.country,
    lat = localCity.lat,
    lon = localCity.lon,
    state = localCity.state,
)
