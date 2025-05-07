package org.cats.onlinebank.core.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.cats.onlinebank.core.common.ResourcesProvider
import org.cats.onlinebank.core.common.dispatcher.AppDispatchers
import org.cats.onlinebank.core.common.dispatcher.AppDispatchersImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
  singleOf(::AppDispatchersImpl) { bind<AppDispatchers>() }

  single<CoroutineScope>(named("AppCoroutineScope")) {
    CoroutineScope(get<AppDispatchersImpl>().io + SupervisorJob())
  }

  single { ResourcesProvider(get()) }
}
