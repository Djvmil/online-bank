package org.cats.onlinebank.core.feature.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cats.onlinebank.core.common.dispatcher.AppDispatchers
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.common.utils.Utils
import org.cats.onlinebank.core.domain.usecase.GetAccountDetailUseCase
import org.cats.onlinebank.core.feature.navigation.DestinationsArgs
import org.cats.onlinebank.core.feature.ui.home.model.UiAccountModel
import org.cats.onlinebank.core.feature.ui.home.model.toUiAccountModel

class DetailViewModel(
    private val getAccountDetailUseCase: GetAccountDetailUseCase,
    private val appDispatchers: AppDispatchers,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val _uiState = MutableStateFlow<OBResult<UiAccountModel, OBError>>(OBResult.Loading)
  val uiState = _uiState.asStateFlow()


    init {
        val bankName = savedStateHandle.get<String>(DestinationsArgs.BANK_NAME_ARG)
        val accountId = savedStateHandle.get<String>(DestinationsArgs.ACCOUNT_DETAIL_ID_ARG)

        bankName?.let {
            accountId?.let {
                Logger.e(tag = TAG, messageString =  "Error fetching data: $bankName, $accountId")

                fetchDetail(Pair(Utils.decode(bankName), Utils.decode(accountId)))
            } ?: run {
                _uiState.update { OBResult.Failure(OBError(throwable = Throwable("Account ID is null"))) }
                Logger.e( tag = TAG, messageString =  "Error fetching data: Account ID is null")
            }
        } ?: run {
            Logger.e( tag = TAG, messageString =  "Error fetching data: Bank Name is null")
            _uiState.update { OBResult.Failure(OBError(throwable = Throwable("Bank Name is null"))) }
        }
    }

  private fun fetchDetail(key: Pair<String, String>) {
    viewModelScope.launch {
      getAccountDetailUseCase(key)
          .flowOn(appDispatchers.io)
          .catch { throwable ->
              Logger.e(tag = TAG, messageString =  "Error fetching data:", throwable = throwable)
              _uiState.value = OBResult.Failure(OBError(throwable = Throwable("Error fetching data:")))
          }
          .collect { result ->
              Logger.e( "$result")

              when (result) {
                  is OBResult.Loading ->  _uiState.value = OBResult.Loading
                  is OBResult.Failure -> {
                      _uiState.value = OBResult.Failure(result.error)
                  }
                  is OBResult.Success -> _uiState.value = OBResult.Success(result.value.toUiAccountModel())
              }
          }
    }
  }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}