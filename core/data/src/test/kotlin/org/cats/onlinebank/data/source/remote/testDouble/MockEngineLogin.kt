package org.cats.onlinebank.data.source.remote.testDouble

import org.cats.onlinebank.data.common.ApiResponseType
import org.cats.onlinebank.data.common.FAKE_DATA
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import org.cats.onlinebank.core.data.source.remote.util.Route

object MockEngineLogin {
  private val Url.hostWithPortIfRequired: String
    get() = if (port == protocol.defaultPort) host else hostWithPort

  private val Url.fullUrl: String
    get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

  var type = ApiResponseType.RESPONSE_DATA_VALID

  val engine = MockEngine { request ->
    if (request.url.fullUrl.endsWith(Route.BANK_LIST_URL)) {
      val responseHeaders = headersOf("Content-Type" to listOf("application/json", "charset=utf-8"))

      when (type) {
        /** define what the response should be based on which [type] is passed */
        ApiResponseType.RESPONSE_DATA_VALID -> {
          respond(
              content = FAKE_DATA.LOGIN_RESPONSE_SUCCESS,
              status = HttpStatusCode.OK,
              headers = responseHeaders)
        }
        ApiResponseType.RESPONSE_DATA_INVALID -> {
          respond(
              content = FAKE_DATA.INVALID_RESPONSE,
              status = HttpStatusCode.OK,
              headers = responseHeaders)
        }
        ApiResponseType.RESPONSE_DATA_EMPTY -> {
          respond(
              content = FAKE_DATA.EMPTY_RESPONSE,
              status = HttpStatusCode.OK,
              headers = responseHeaders)
        }
      }
    } else {
      throw Exception("Stub!")
    }
  }
}
