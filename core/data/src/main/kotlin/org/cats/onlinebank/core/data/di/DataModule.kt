package org.cats.onlinebank.core.data.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.cats.onlinebank.core.common.di.commonModule
import org.cats.onlinebank.core.data.repository.BankListRepository
import org.cats.onlinebank.core.data.repository.BankListRepositoryImpl
import org.cats.onlinebank.core.data.source.remote.api.ApiService
import org.cats.onlinebank.core.data.source.remote.api.ApiServiceImpl
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.data.source.remote.util.Route
import org.koin.dsl.module

val dataModule = module {
    includes(commonModule)

    single { Json { ignoreUnknownKeys = true } }
    single<BankListRepository> {
        BankListRepositoryImpl(api = get())
    }

    single<ApiService> { ApiServiceImpl(httpClient = get()) }
    single { provideKtorClient() }
}

fun provideKtorClient(): HttpClient {
    return HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
            logger =
                object : Logger {
                    override fun log(message: String) {
                        Log.i("HttpClient", message)
                    }
                }
        }
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                when (exception) {
                    is JsonConvertException -> {
                        val body = request.call.response.body<List<BankApiModel>>() as? List<BankApiModel>
                        Log.e("TAG", "provideKtorClient: $body", )
                        throw Exception()
                    }
                    else -> {
                        throw Exception()
                    }
                }
            }
        }

        install(ResponseObserver) {
            onResponse { response -> Log.i("HTTP status", response.status.value.toString()) }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 10_000
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 10_000
        }
        install(DefaultRequest) {
            url{
                protocol = URLProtocol.HTTPS
                host = Route.BASE_URL
            }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.Accept, ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    coerceInputValues = true
                })
        }
    }
}
