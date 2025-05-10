package org.cats.onlinebank.core.data.di

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.FlowConverterFactory
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.cats.onlinebank.core.common.di.commonModule
import org.cats.onlinebank.core.data.repository.BankListRepository
import org.cats.onlinebank.core.data.repository.BankListRepositoryImpl
import org.cats.onlinebank.core.data.source.remote.api.ApiService
import org.cats.onlinebank.core.data.source.remote.api.ApiServiceImpl
import org.cats.onlinebank.core.data.source.remote.util.Route
import org.koin.dsl.module

val dataModule = module {
    includes(commonModule)
    single<Ktorfit> {
        ktorfit {
            baseUrl(Route.BASE_URL)
            httpClient(
                HttpClient{
                    install(ContentNegotiation) {
                        json(
                            Json {
                                ignoreUnknownKeys = true
                                prettyPrint = true
                                isLenient = true
                                coerceInputValues = true
                            }
                        )
                    }
                },
            )
            converterFactories(
                FlowConverterFactory(),
                CallConverterFactory(),
            )
        }
    }

    single { Json { ignoreUnknownKeys = true } }
    single<BankListRepository> {
        BankListRepositoryImpl(api = get())
    }

    single<ApiService> { ApiServiceImpl(ktorfit = get()) }
}