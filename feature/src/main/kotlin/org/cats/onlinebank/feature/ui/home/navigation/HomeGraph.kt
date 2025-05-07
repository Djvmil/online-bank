package org.cats.onlinebank.feature.ui.home.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.cats.onlinebank.feature.ui.home.HomeScreen
import org.cats.onlinebank.feature.navigation.Destinations
import org.cats.onlinebank.feature.navigation.NavigationHelpers
import org.cats.onlinebank.feature.ui.detail.navigation.navigateToDetail

fun NavGraphBuilder.home(navActions: NavigationHelpers) {
  val route = Destinations.HOME_ROUTE

  composable(
      route,
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
          HomeScreen(onDetailClick = { bankName, accountId ->
              navActions.navigateToDetail(bankName, accountId)
          })
      }
}

fun NavigationHelpers.navigateToHome() {
  navController.navigate(Destinations.HOME_ROUTE)
}

