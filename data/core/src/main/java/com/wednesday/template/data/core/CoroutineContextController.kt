package com.wednesday.template.data.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface CoroutineContextController {

    val dispatcherDefault: CoroutineDispatcher

    val dispatcherIO: CoroutineDispatcher

    suspend fun <T> switchToDefault(block: suspend CoroutineScope.() -> T): T

    suspend fun <T> switchToIO(block: suspend CoroutineScope.() -> T): T
}
