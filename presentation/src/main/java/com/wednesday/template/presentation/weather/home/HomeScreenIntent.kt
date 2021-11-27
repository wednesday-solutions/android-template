package com.wednesday.template.presentation.weather.home

import com.wednesday.template.presentation.base.intent.Intent
import com.wednesday.template.presentation.screen.Screen

interface HomeScreenIntent : Intent {

    object Search : HomeScreenIntent
}
