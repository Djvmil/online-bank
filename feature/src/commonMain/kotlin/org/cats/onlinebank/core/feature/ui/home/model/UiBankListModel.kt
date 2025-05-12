package org.cats.onlinebank.core.feature.ui.home.model

import org.cats.onlinebank.core.data.source.remote.model.BankApiModel


data class UiBankListModel(val label: String, val banks: List<UiBankModel>)

fun List<BankApiModel>.toUiBankListModel():  List<UiBankListModel> {
    return this.groupBy { bank ->
        if (bank.isCA == 1) "Credit Agricole"
        else "Autres Banques"
    }.toList()
        .sortedByDescending { (key, _) -> key == "Credit Agricole" }
        .map { (key, value) ->
            UiBankListModel(
                label = key,
                banks = value.map { bank ->
                    bank.toUiBankModel()
                }
            )
        }
}