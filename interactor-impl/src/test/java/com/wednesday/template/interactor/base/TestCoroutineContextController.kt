package com.wednesday.template.interactor.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
data class TestCoroutineContextController(
    val testCoroutineDispatcher: TestDispatcher
) : CoroutineContextController {

    override val dispatcherDefault: CoroutineDispatcher
        get() = testCoroutineDispatcher
    override val dispatcherIO: CoroutineDispatcher
        get() = testCoroutineDispatcher

    override suspend fun <T> switchToDefault(block: suspend CoroutineScope.() -> T): T {
        return withContext(testCoroutineDispatcher) { block() }
    }

    override suspend fun <T> switchToIO(block: suspend CoroutineScope.() -> T): T {
        return withContext(testCoroutineDispatcher) { block() }
    }
}
