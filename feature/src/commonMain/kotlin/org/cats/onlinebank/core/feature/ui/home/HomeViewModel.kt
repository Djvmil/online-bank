package org.cats.onlinebank.core.feature.ui.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.cats.onlinebank.core.common.dispatcher.AppDispatchers
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.domain.usecase.GetBankListUseCase
import org.cats.onlinebank.core.feature.ui.detail.DetailViewModel
import org.cats.onlinebank.core.feature.ui.detail.DetailViewModel.Companion
import org.cats.onlinebank.core.feature.ui.home.model.UiBankListModel
import org.cats.onlinebank.core.feature.ui.home.model.toUiBankListModel

class HomeViewModel(
    private val getBankListUseCase: GetBankListUseCase,
    private val appDispatchers: AppDispatchers
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
          .catch {
              throwable ->
              Logger.e(tag = TAG, messageString =  "Error fetching data:", throwable = throwable)
              OBResult.Failure(OBError(throwable = throwable))
          }
          .collect { result ->
              when(result) {
                  is OBResult.Loading -> _uiBanks.value = OBResult.Loading
                  is OBResult.Failure -> {
                      _uiBanks.value = OBResult.Failure(result.error)
                  }
                  is OBResult.Success -> _uiBanks.value =
                      OBResult.Success(result.value.toUiBankListModel())
              }
          }
    }
  }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}
