package org.cats.onlinebank.core.feature.ui.detail.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.cats.onlinebank.core.common.utils.Utils
import org.cats.onlinebank.core.feature.navigation.Destinations
import org.cats.onlinebank.core.feature.navigation.DestinationsArgs
import org.cats.onlinebank.core.feature.navigation.NavigationHelpers
import org.cats.onlinebank.core.feature.ui.detail.DetailScreen

fun NavGraphBuilder.detail(navActions: NavigationHelpers) {
  composable(
      route = Destinations.BANK_DETAIL_ARG_ROUTE,
      arguments =
          listOf(
              navArgument(DestinationsArgs.BANK_NAME_ARG) { type = NavType.StringType },
              navArgument(DestinationsArgs.ACCOUNT_DETAIL_ID_ARG) { type = NavType.StringType }
          ),
      enterTransition = {
        return@composable fadeIn(tween(1000))
      },
      exitTransition = {
        return@composable slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
      },
      popEnterTransition = {
        return@composable slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.End, tween(700))
      }) {
        DetailScreen(
            onBackClicked = { navActions.navigateUp() })
      }
}

fun NavigationHelpers.navigateToDetail(bankName: String, accountId: String) {
  navController.navigate(
      "${Destinations.BANK_DETAIL_ROUTE}/${Utils.encode(bankName)}/${Utils.encode(accountId)}")
}