package org.cats.onlinebank.core.domain

import app.cash.turbine.test
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.common.test.MainDispatcherRule
import org.cats.onlinebank.core.data.repository.BankListRepository
import org.cats.onlinebank.core.domain.usecase.GetBankListUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetBankListUseCaseTest {
  @get:Rule val mockkRule = MockKRule(this)

  @get:Rule val mainDispatcherRule = MainDispatcherRule()

  @MockK lateinit var bankListRepository: BankListRepository
  private lateinit var getBankListUseCase: GetBankListUseCase

  @BeforeTest
  fun setup() {
    getBankListUseCase = GetBankListUseCase(bankListRepository)
  }

  @Test
  fun test_invoke_success_banks_use_case() = runTest {
    // GIVEN
    val expectedDataRequest = FAKE_DATA.fakeBanksResult
    coEvery { bankListRepository.bankList() } returns
        flowOf(OBResult.Success(expectedDataRequest))

    // WHEN
    val actualResponse = getBankListUseCase.invoke()

    // THEN
    actualResponse.test {
      assertEquals(awaitItem(), OBResult.Success(expectedDataRequest))
      awaitComplete()
    }
  }

  @Test
  fun test_invoke_error_banks_use_case() = runTest {
    // GIVEN
    coEvery { bankListRepository.bankList()} returns
        flowOf(OBResult.Failure(OBError("error")))

    // WHEN
    val actualResponse = getBankListUseCase.invoke()

    // THEN
    actualResponse.test {
      assertEquals(awaitItem(), OBResult.Failure(OBError("error")))
      awaitComplete()
    }
  }
}
