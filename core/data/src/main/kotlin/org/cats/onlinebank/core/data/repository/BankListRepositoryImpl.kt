package org.cats.onlinebank.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.data.source.remote.api.ApiService
import org.cats.onlinebank.core.data.source.remote.model.AccountApiModel
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.data.source.remote.util.onFailure
import org.cats.onlinebank.core.data.source.remote.util.onSuccess

class BankListRepositoryImpl(
    val api: ApiService
) : BankListRepository {
    private var bankList: List<BankApiModel>? = null

  override suspend fun getFromApi(): Flow<OBResult<List<BankApiModel>, OBError>> = flow {
      emit(OBResult.Loading)
        api
            .bankList()
            .onSuccess { response ->
                bankList = response
                emit(OBResult.Success(response))
            }
            .onFailure { error -> emit(OBResult.Failure(OBError(throwable = error))) }
  }

    override suspend fun bankList(): Flow<OBResult<List<BankApiModel>, OBError>> = if (bankList != null) flow {
            emit(OBResult.Success(bankList!!))
    } else getFromApi()

    override suspend fun accountDetail(key: Pair<String, String>): Flow<OBResult<AccountApiModel, OBError>> = flow{
        if (bankList != null) {
            bankList!!.find { it.name == key.first }?.let { bank ->
                val accountList = bank.accounts.filter { it.id == key.second }.first()
                emit(OBResult.Success(accountList))
            } ?: run {
                emit(OBResult.Failure(OBError(throwable = Throwable("Account not found"))))
            }
            return@flow
        }
    }

}
