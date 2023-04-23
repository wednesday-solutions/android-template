package com.wednesday.template.home.presentation

import com.wednesday.template.presentation.base.intent.Intent

sealed interface HomeScreenIntent : Intent {

    object Search : HomeScreenIntent

    object Loading : HomeScreenIntent
}
