package org.cats.onlinebank.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.common.model.mapOB
import org.cats.onlinebank.core.data.repository.BankListRepository
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.domain.util.UseCaseNoInput

class GetBankListUseCase internal constructor(private val repository: BankListRepository) :
    UseCaseNoInput<Flow<OBResult<List<BankApiModel>, OBError>>> {

  override suspend fun invoke(): Flow<OBResult<List<BankApiModel>, OBError>> =
      repository.bankList().map { bankList ->
          bankList.mapOB {
                it.sortedBy { bank -> bank.name }
          }
      }
}
