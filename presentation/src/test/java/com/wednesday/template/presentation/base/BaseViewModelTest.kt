package com.wednesday.template.presentation.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.kotlin.mock

abstract class BaseViewModelTest {

    @get:Rule
    val mainDispatcherTestRule = MainDispatcherTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    abstract fun before()

    abstract fun after()

    @Before
    fun internalBefore() {
        val testModule = module {
            factory { SavedStateHandle() }
        }
        startKoin {
            modules(testModule)
        }
        before()
    }

    @Before
    fun internalAfter() {
        after()
        stopKoin()
    }

    protected fun <T> mockObserver() = mock<Observer<T?>>()
}