package org.cats.onlinebank.feature.ui.home

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.cats.onlinebank.core.common.dispatcher.AppDispatchers
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.domain.usecase.GetBankListUseCase
import org.cats.onlinebank.feature.ui.home.model.UiBankListModel
import org.cats.onlinebank.feature.ui.home.model.toUiBankListModel
import org.cats.onlinebank.feature.util.ResourcesProvider

private const val TAG = "HomeViewModel"
class HomeViewModel(
    private val getBankListUseCase: GetBankListUseCase,
    private val appDispatchers: AppDispatchers,
    private val resProvider: ResourcesProvider
) : ViewModel() {
  private val _uiBanks = MutableStateFlow<OBResult<List<UiBankListModel>, OBError>>(OBResult.Loading)
  val uiBanks = _uiBanks.asStateFlow()
  init {
    fetchBankList()
  }

    @VisibleForTesting
   fun fetchBankList() {
    viewModelScope.launch {
      getBankListUseCase.invoke()
          .flowOn(appDispatchers.io)
          .catch { throwable -> OBResult.Failure(OBError(throwable = throwable)) }
          .collect { result ->
              
              when(result) {
                  is OBResult.Loading -> _uiBanks.value = OBResult.Loading
                  is OBResult.Failure -> {
                      _uiBanks.value = OBResult.Failure(result.error)
                  }
                  is OBResult.Success -> _uiBanks.value = OBResult.Success(result.value.toUiBankListModel(resProvider))
              }
          }
    }
  }
}
