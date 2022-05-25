package com.wednesday.template.repo.weather.models

import com.wednesday.template.domain.weather.City

val cityMappedFromLocalCity = City(
    id = localCity.woeid,
    title = localCity.title,
    country = localCity.locationType,
    latitude = localCity.latitude
)

val cityMappedFromRemoteCity = City(
    id = remoteCity.woeid,
    title = remoteCity.title,
    country = remoteCity.locationType,
    latitude = remoteCity.latitude
)
