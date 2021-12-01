package com.wednesday.template.presentation.base.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface CoroutineContextController {

    val default: CoroutineDispatcher

    val io: CoroutineDispatcher

    suspend fun <T> switchToDefault(block: suspend CoroutineScope.() -> T): T

    suspend fun <T> switchToIO(block: suspend CoroutineScope.() -> T): T
}
