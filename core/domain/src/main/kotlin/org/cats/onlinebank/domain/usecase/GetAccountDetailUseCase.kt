package org.cats.onlinebank.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.common.model.mapOB
import org.cats.onlinebank.core.data.repository.BankListRepository
import org.cats.onlinebank.core.data.source.remote.model.AccountApiModel
import org.cats.onlinebank.core.data.source.remote.model.OperationApiModel
import org.cats.onlinebank.domain.util.UseCase

class GetAccountDetailUseCase internal constructor(private val repository: BankListRepository) :
    UseCase<Pair<String, String>, Flow<OBResult<AccountApiModel, OBError>>> {

    override suspend fun invoke(input: Pair<String, String>):
            Flow<OBResult<AccountApiModel, OBError>> = repository.accountDetail(input)
                .map { account ->
                    account.mapOB {
                        val sortedOperation = it.operations.sortedWith(
                            compareByDescending<OperationApiModel> { operation -> operation.date }
                                .thenBy {
                                    operation -> operation.title
                                }
                        )
                        it.copy(
                            operations = sortedOperation
                        )
                    }

                }
}
