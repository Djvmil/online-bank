package org.cats.onlinebank.di

import org.cats.onlinebank.core.data.di.dataModule
import org.cats.onlinebank.core.domain.di.domainModule
import org.cats.onlinebank.feature.di.featureModule
import org.koin.dsl.module


object ProvideAppModules {
  fun getAppModules() = module {
    includes(
        dataModule,
        domainModule,
        featureModule,
    )
  }
}
