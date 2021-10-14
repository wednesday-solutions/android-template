package com.wednesday.template

import android.app.Application
import com.wednesday.template.service_di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidTemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                serviceModule
            )
        }
    }

}
