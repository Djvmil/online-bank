package org.cats.onlinebank

import android.app.Application
import org.cats.onlinebank.di.ProvideAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OlineBankApp  : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@OlineBankApp)
            modules(ProvideAppModules.getAppModules())
        }
    }
}