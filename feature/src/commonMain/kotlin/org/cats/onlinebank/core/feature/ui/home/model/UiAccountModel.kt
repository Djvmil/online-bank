package org.cats.onlinebank.core.feature.ui.home.model

import org.cats.onlinebank.core.data.source.remote.model.AccountApiModel

data class UiAccountModel(
    val balance: Double,
    val holder: String,
    val id: String,
    val label: String,
    val operations: List<UiAccountOperationModel>,
    val order: Int,
)

fun AccountApiModel.toUiAccountModel(): UiAccountModel {
    return UiAccountModel(
        balance = this.balance,
        holder = this.holder,
        id = this.id,
        label = this.label,
        operations = this.operations.map { it.toUiAccountOperationModel() },
        order = this.order
    )
}
