package org.cats.onlinebank.core.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class BankApiModel(
    val accounts: List<AccountApiModel>,
    val isCA: Int,
    val name: String
)