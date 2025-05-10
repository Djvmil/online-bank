package org.cats.onlinebank.core.data.common

import org.cats.onlinebank.core.data.di.dataModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class ModuleCheckTest {
  @BeforeTest
  fun setUp() {
    loadKoinModules(dataModule)
  }

  @AfterTest
  fun tearDown() {
    unloadKoinModules(dataModule)
  }
}
