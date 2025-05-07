package org.cats.onlinebank.data.common

import org.cats.onlinebank.core.data.source.remote.model.AccountApiModel
import org.cats.onlinebank.core.data.source.remote.model.BankApiModel
import org.cats.onlinebank.core.data.source.remote.model.OperationApiModel


object FAKE_DATA {
  val INVALID_RESPONSE = "remote/invalid_response_file.json".readFile()
  val EMPTY_RESPONSE = "remote/empty_response_file.json".readFile()
  val LOGIN_RESPONSE_SUCCESS = "remote/login_response_success_file.json".readFile()

  val fakeBanksResult = listOf(
      BankApiModel(
          name = "CA Languedoc",
          isCA = 1,
          accounts = listOf(
              AccountApiModel(
                  order = 1,
                  id = "151515151151",
                  holder = "Corinne Martin",
                  role = 1,
                  contractNumber = "32216549871",
                  label = "Compte de dépôt",
                  productCode = "00004",
                  balance = 2031.84,
                  operations = listOf(
                      OperationApiModel(
                          id = "2",
                          title = "Prélèvement Netflix",
                          amount = "-15,99",
                          category = "leisure",
                          date = "1644870724"
                      )
                  )
              )
          )
      )
  )

}
