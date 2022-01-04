package com.wednesday.template.repo.weather.models

import com.wednesday.template.domain.weather.City

val cityMappedFromLocalCity = City(
    id = localCity.woeid,
    title = localCity.title,
    locationType = localCity.locationType,
    latitude = localCity.latitude
)

val cityMappedFromRemoteCity = City(
    id = remoteCity.woeid,
    title = remoteCity.title,
    locationType = remoteCity.locationType,
    latitude = remoteCity.latitude
)
