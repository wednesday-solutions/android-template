package com.wednesday.template.presentation.base.intent

interface IntentHandler<I : Intent> {

    fun onIntent(intent: I)
}
