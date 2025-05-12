package org.cats.onlinebank.core.feature

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.cats.onlinebank.core.common.ResourcesProvider
import org.cats.onlinebank.core.common.dispatcher.AppDispatchers
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.domain.usecase.GetBankListUseCase
import org.cats.onlinebank.core.feature.ui.home.HomeViewModel
import org.cats.onlinebank.core.feature.ui.home.model.toUiBankListModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import app.cash.turbine.test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private val getBankListUseCase: GetBankListUseCase = mockk(relaxed = true)
    private val resProvider: ResourcesProvider = mockk()
    private val appDispatchers: AppDispatchers = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { appDispatchers.io} returns testDispatcher
        viewModel = HomeViewModel(getBankListUseCase, appDispatchers, resProvider)
    }

    @Test
    fun `fetchBankList should emit success state`() = runTest {
        // GIVEN
        val mockData = FAKE_DATA.fakeBanksResult
        coEvery { resProvider.getString(any()) } returns "Credit Agricole"
        coEvery { getBankListUseCase() } returns flow {
            emit(OBResult.Success(mockData))
        }

        // WHEN
        viewModel.fetchBankList()

        // THEN
        viewModel.uiBanks.test {
            assertEquals(OBResult.Loading, awaitItem())
            assertEquals(OBResult.Success(mockData.toUiBankListModel(resProvider)), awaitItem())
        }

    }

    @Test
    fun `fetchBankList should emit failure state`() = runTest {
        // GIVEN
        val mockError = Throwable("Error fetching data")
        coEvery { getBankListUseCase() } returns flow {
            emit(OBResult.Failure(OBError(throwable = mockError)))
        }

        // WHEN
        viewModel.fetchBankList()

        // THEN
        viewModel.uiBanks.test {
            assertEquals(OBResult.Loading, awaitItem())
            val result = awaitItem() as OBResult.Failure
            assertEquals("Error fetching data", result.error.throwable?.message)

       }
    }
}