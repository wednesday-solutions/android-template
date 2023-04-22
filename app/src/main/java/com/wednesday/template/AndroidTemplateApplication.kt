package com.wednesday.template

import android.app.Application
import com.wednesday.template.domain.domainModule
import com.wednesday.template.navigation.navigationModule
import com.wednesday.template.repo.repoModule
import com.wednesday.template.service.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AndroidTemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(applicationContext)
            modules(
                serviceModule,
                repoModule,
                domainModule,
                interactorModule,
                navigationModule,
//                presentationModule
            )
        }
    }
}
