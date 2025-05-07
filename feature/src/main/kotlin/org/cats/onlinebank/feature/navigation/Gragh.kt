package org.cats.onlinebank.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import org.cats.onlinebank.feature.ui.detail.navigation.detail
import org.cats.onlinebank.feature.ui.home.navigation.home

@Composable
fun NavGraph(
    navController: NavHostController,
    navActions: NavigationHelpers,
    modifier: Modifier = Modifier,
) {
  val startDestination: String = Destinations.HOME_ROUTE

  val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination
  NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
    home(navActions = navActions)
    detail(navActions = navActions)
  }
}
