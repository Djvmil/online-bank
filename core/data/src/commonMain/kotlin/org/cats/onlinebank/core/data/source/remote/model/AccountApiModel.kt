package org.cats.onlinebank.core.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountApiModel(
    val balance: Double,
    @SerialName("contract_number") val contractNumber: String,
    val holder: String,
    val id: String,
    val label: String,
    val operations: List<OperationApiModel>,
    val order: Int,
    @SerialName("product_code") val productCode: String,
    val role: Int
)