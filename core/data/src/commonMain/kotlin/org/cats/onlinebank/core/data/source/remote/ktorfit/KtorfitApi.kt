package org.cats.onlinebank.core.data.source.remote.model

import de.jensklingenberg.ktorfit.http.GET
import org.cats.onlinebank.core.data.source.remote.util.Route


internal interface KtorfitApi{
    @GET(Route.BANK_LIST_URL)
    suspend fun bankList(): List<BankApiModel>
}