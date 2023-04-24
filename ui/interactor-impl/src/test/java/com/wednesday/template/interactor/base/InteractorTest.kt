package com.wednesday.template.interactor.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule

@ExperimentalCoroutinesApi
open class InteractorTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    fun TestScope.launchInTestScope(block: suspend CoroutineScope.() -> Unit): Job {
        return launch(UnconfinedTestDispatcher(testScheduler)) {
            block()
        }
    }
}
