package org.cats.onlinebank.core.feature.ui.home.model

import org.cats.onlinebank.core.common.utils.DateTimeKtx
import org.cats.onlinebank.core.data.source.remote.model.OperationApiModel

data class UiAccountOperationModel(
    val amount: String,
    val date: String,
    val title: String
)

fun OperationApiModel.toUiAccountOperationModel(): UiAccountOperationModel {
    return UiAccountOperationModel(
        amount = this.amount,
        date = DateTimeKtx.getFormattedDate(this.date),
        title = this.title
    )
}