package org.cats.onlinebank.core.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class OperationApiModel(
    val amount: String,
    val category: String,
    val date: String,
    val id: String,
    val title: String
)