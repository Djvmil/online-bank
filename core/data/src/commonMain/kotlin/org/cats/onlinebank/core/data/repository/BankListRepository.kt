package org.cats.onlinebank.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.data.source.remote.model.AccountApiModel
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel

interface BankListRepository {
  suspend fun getFromApi(): Flow<OBResult<List<BankApiModel>, OBError>>
  suspend fun bankList(): Flow<OBResult<List<BankApiModel>, OBError>>
  suspend fun accountDetail(key: Pair<String, String>): Flow<OBResult<AccountApiModel, OBError>>
}
