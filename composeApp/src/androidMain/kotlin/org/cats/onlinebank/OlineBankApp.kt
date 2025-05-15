package org.cats.onlinebank

import android.app.Application
import org.cats.onlinebank.di.ProvideAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
class OlineBankApp  : Application(), KoinStartup {

    override fun onKoinStartup() = KoinConfiguration {
        androidLogger()
        androidContext(this@OlineBankApp)
        modules(ProvideAppModules.getAppModules())
    }
}