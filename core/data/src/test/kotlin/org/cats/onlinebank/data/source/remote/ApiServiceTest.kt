package org.cats.onlinebank.data.source.remote

import kotlinx.coroutines.test.runTest
import org.cats.onlinebank.core.common.test.MainDispatcherRule
import org.cats.onlinebank.core.data.source.remote.api.ApiService
import org.cats.onlinebank.core.data.source.remote.util.onSuccess
import org.cats.onlinebank.data.common.ApiResponseType
import org.cats.onlinebank.data.common.FAKE_DATA
import org.cats.onlinebank.data.source.remote.testDouble.ApiServiceFake
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class ApiServiceTest {
  @get:Rule val mainDispatcherRule = MainDispatcherRule()
  private lateinit var apiService: ApiService

  @Test
  fun banks_Assert_Response_Success() = runTest {
    // GIVEN
    apiService = ApiServiceFake.build(ApiResponseType.RESPONSE_DATA_VALID)

    // WHEN
    val actualResult = apiService.bankList()

    // THEN
    actualResult.onSuccess {
      assertEquals(it.first().name, FAKE_DATA.fakeBanksResult.first().name)
      assertEquals(it.first().isCA, FAKE_DATA.fakeBanksResult.first().isCA)
    }
  }
}
