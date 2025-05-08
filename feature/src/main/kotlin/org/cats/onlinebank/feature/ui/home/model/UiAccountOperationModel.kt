package org.cats.onlinebank.feature.ui.home.model

import org.cats.onlinebank.core.common.utils.DateUtil
import org.cats.onlinebank.core.data.source.remote.model.OperationApiModel

data class UiAccountOperationModel(
    val amount: String,
    val date: String,
    val title: String
)

fun OperationApiModel.toUiAccountOperationModel(): UiAccountOperationModel {
    return UiAccountOperationModel(
        amount = this.amount,
        date = DateUtil.convertTimestampToDate(this.date.toLong()).toString(),
        title = this.title
    )
}