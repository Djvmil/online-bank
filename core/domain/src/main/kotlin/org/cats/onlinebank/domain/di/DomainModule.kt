package org.cats.onlinebank.domain.di

import kotlinx.coroutines.flow.Flow
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.data.source.remote.model.AccountApiModel
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.domain.usecase.GetBankListUseCase
import org.cats.onlinebank.domain.usecase.GetAccountDetailUseCase
import org.cats.onlinebank.domain.util.UseCase
import org.cats.onlinebank.domain.util.UseCaseNoInput
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
  singleOf(::GetBankListUseCase) {
    bind<UseCaseNoInput<Flow<OBResult<List<BankApiModel>, OBError>>>>()
  }

  singleOf(::GetAccountDetailUseCase) {
    bind<UseCase<Pair<String, String>, Flow<OBResult<AccountApiModel, OBError>>>>()
  }
}
