package org.cats.onlinebank.core.data.source.remote.testDouble

import org.cats.onlinebank.core.data.source.remote.api.ApiService
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.data.source.remote.util.ApiOperation

class ApiServiceFake: ApiService {

    override suspend fun bankList(): ApiOperation<List<BankApiModel>> {
        TODO("Not yet implemented")
    }
}
