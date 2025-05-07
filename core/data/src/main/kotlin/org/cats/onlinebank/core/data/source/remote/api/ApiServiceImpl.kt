package org.cats.onlinebank.core.data.source.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.data.source.remote.util.ApiOperation
import org.cats.onlinebank.core.data.source.remote.util.Route
import org.cats.onlinebank.core.data.source.remote.util.safeApiCall

class ApiServiceImpl(val httpClient: HttpClient) : ApiService {
  override suspend fun bankList(): ApiOperation<List<BankApiModel>> {
    return safeApiCall {
      httpClient
        .get {
          url(Route.BANK_LIST_URL)
          accept(ContentType.Application.Json)
        }.body()
    }
  }


}
