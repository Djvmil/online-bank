package org.cats.onlinebank.feature.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cats.onlinebank.core.common.dispatcher.AppDispatchers
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.domain.usecase.GetAccountDetailUseCase
import org.cats.onlinebank.feature.navigation.DestinationsArgs
import org.cacts.onlinebank.feature.R
import org.cats.onlinebank.feature.ui.home.model.UiAccountModel
import org.cats.onlinebank.feature.ui.home.model.toUiAccountModel
import org.cats.onlinebank.feature.util.ResourcesProvider

private const val TAG = "DetailViewModel"
class DetailViewModel(
    private val getAccountDetailUseCase: GetAccountDetailUseCase,
    private val appDispatchers: AppDispatchers,
    private val resProvider: ResourcesProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val _uiState = MutableStateFlow<OBResult<UiAccountModel, OBError>>(OBResult.Loading)
  val uiState = _uiState.asStateFlow()


    init {
        val bankName = savedStateHandle.get<String>(DestinationsArgs.BANK_NAME_ARG)
        val accountId = savedStateHandle.get<String>(DestinationsArgs.ACCOUNT_DETAIL_ID_ARG)

        bankName?.let {
            accountId?.let {
                fetchDetail(Pair(bankName, accountId))
            } ?: run {
                _uiState.update { OBResult.Failure(OBError(throwable = Throwable("Account ID is null"))) }
                Log.e(TAG, "Error fetching data: Account ID is null")
            }
        } ?: run {
            Log.e(TAG, "Error fetching data: Bank Name is null")
            _uiState.update { OBResult.Failure(OBError(throwable = Throwable("Bank Name is null"))) }
        }
    }

  private fun fetchDetail(key: Pair<String, String>) {
    viewModelScope.launch {
      getAccountDetailUseCase(key)
          .flowOn(appDispatchers.io)
          .catch { throwable ->
              Log.e(TAG, resProvider.getString(R.string.feature_error_fetching_data), throwable)
              _uiState.value = OBResult.Failure(OBError(throwable = Throwable(resProvider.getString(R.string.feature_error_fetching_data))))
          }
          .collect { result ->
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
}
