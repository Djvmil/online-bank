package org.cats.onlinebank.feature.ui.home.model

import org.cats.onlinebank.core.common.ResourcesProvider
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cacts.onlinebank.feature.R


data class UiBankListModel(val label: String, val banks: List<UiBankModel>)

fun List<BankApiModel>.toUiBankListModel(resProvider: ResourcesProvider):  List<UiBankListModel> {
    return this.groupBy { bank ->
        if (bank.isCA == 1) resProvider.getString(R.string.feature_credit_agricole_label)
        else resProvider.getString(R.string.feature_other_banks_label)
    }.toSortedMap(compareByDescending { key -> key == resProvider.getString(R.string.feature_credit_agricole_label)  }).map { (key, value) ->
        UiBankListModel(
            label = key,
            banks = value.map { bank ->
                bank.toUiBankModel()
            }
        )
    }
}