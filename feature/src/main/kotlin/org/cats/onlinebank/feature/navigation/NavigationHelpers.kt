package org.cats.onlinebank.feature.navigation

import androidx.navigation.NavHostController

internal object DestinationsArgs {
  const val ACCOUNT_DETAIL_ID_ARG = "account_id"
  const val BANK_NAME_ARG = "bank_name"
}

internal object Destinations {
  const val HOME_ROUTE = "home"
  const val BANK_DETAIL_ROUTE = "detail"
  const val BANK_DETAIL_ARG_ROUTE = "detail/{bank_name}/{account_id}"

}

class NavigationHelpers(internal val navController: NavHostController) {
  fun navigateUp() {
    navController.navigateUp()
  }

}
