package org.cats.onlinebank.core.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class BankAccountApiModel(
    val banks: List<BankApiModel>
)