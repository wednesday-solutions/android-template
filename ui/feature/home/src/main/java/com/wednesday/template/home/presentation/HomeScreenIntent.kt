package com.wednesday.template.home.presentation

import com.wednesday.template.presentation.base.intent.Intent

sealed interface HomeScreenIntent : Intent {

    object Search : HomeScreenIntent

    object Loading : HomeScreenIntent
    object Loading2 : HomeScreenIntent
    object Loading3 : HomeScreenIntent
}
