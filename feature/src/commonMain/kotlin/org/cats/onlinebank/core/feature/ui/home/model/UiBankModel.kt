package org.cats.onlinebank.core.feature.ui.home.model

import org.cats.onlinebank.core.data.source.remote.model.BankApiModel

data class UiBankModel(val name: String, val capital: Double, val accounts: List<UiAccountModel>)

fun BankApiModel.toUiBankModel(): UiBankModel {
    return UiBankModel(
        name = this.name,
        capital = this.accounts.sumOf { it.balance },
        accounts = this.accounts.map { it.toUiAccountModel() }
    )
}