package com.wednesday.template.data.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoroutineContextControllerImpl : CoroutineContextController {

    override val dispatcherDefault: CoroutineDispatcher
        get() = Dispatchers.Default

    override val dispatcherIO: CoroutineDispatcher
        get() = Dispatchers.IO

    override suspend fun <T> switchToDefault(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.Default) { block() }
    }

    override suspend fun <T> switchToIO(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.IO) { block() }
    }
}
