package com.wednesday.template.service_entity

enum class WeatherCondition {
  snow,
  sleet,
  hail,
  thunderstorm,
  heavyRain,
  lightRain,
  showers,
  heavyCloud,
  lightCloud,
  clear,
  unknown;

  companion object {
    fun weatherConditionFromAbbreviation(abbreviation: String): WeatherCondition {
      return when(abbreviation) {
        "sn" -> snow
        "sl" -> sleet
        "h" -> hail
        "t" -> thunderstorm
        "hr" -> heavyRain
        "lr" -> lightRain
        "s" -> showers
        "hc" -> heavyCloud
        "lc" -> lightCloud
        "c" -> clear
        else -> unknown
      }
    }
  }

//  fun weatherConditionImageResouceId(): Int {
//    return when(this) {
//      lightCloud, clear -> R.drawable.clear
//      snow, sleet, hail -> R.drawable.snow
//      heavyCloud -> R.drawable.cloudy
//      thunderstorm -> R.drawable.thunderstorm
//      heavyRain, lightRain, showers -> R.drawable.rainy
//      unknown -> R.drawable.clear
//    }
//  }
}
