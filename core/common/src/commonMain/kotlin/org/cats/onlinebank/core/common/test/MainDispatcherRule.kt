package org.cats.onlinebank.core.common.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest

class MainDispatcherRule(
  private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) {
  //@BeforeTest
  fun starting() {
    Dispatchers.setMain(testDispatcher)
  }

  //@AfterTest
  fun finished() {
    Dispatchers.resetMain()
  }
}
