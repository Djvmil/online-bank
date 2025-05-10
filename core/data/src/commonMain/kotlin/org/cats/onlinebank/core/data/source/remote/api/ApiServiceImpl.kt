package org.cats.onlinebank.core.data.source.remote.api

import de.jensklingenberg.ktorfit.Ktorfit
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.data.source.remote.model.KtorfitApi
import org.cats.onlinebank.core.data.source.remote.model.createKtorfitApi
import org.cats.onlinebank.core.data.source.remote.util.ApiOperation
import org.cats.onlinebank.core.data.source.remote.util.safeApiCall

class ApiServiceImpl(
  val ktorfit: Ktorfit
) : ApiService {

  private var ktorfitApi: KtorfitApi = ktorfit.createKtorfitApi()
  override suspend fun bankList(): ApiOperation<List<BankApiModel>> {
    return safeApiCall {
      ktorfitApi.bankList()
    }
  }

}
