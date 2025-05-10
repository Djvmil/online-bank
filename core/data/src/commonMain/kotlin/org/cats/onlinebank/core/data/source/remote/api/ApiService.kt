package org.cats.onlinebank.core.data.source.remote.api

import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.data.source.remote.util.ApiOperation

interface ApiService {
  suspend fun bankList(): ApiOperation<List<BankApiModel>>
}
