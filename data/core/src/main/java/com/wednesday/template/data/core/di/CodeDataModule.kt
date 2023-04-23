package com.wednesday.template.data.core.di

import com.wednesday.template.data.core.CoroutineContextController
import com.wednesday.template.data.core.CoroutineContextControllerImpl
import org.koin.dsl.module

val coreDataModule = module {
    factory<CoroutineContextController> { CoroutineContextControllerImpl() }
}