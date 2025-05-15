package org.cats.onlinebank

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.cats.onlinebank.core.feature.navigation.NavGraph
import org.cats.onlinebank.core.feature.navigation.NavigationHelpers
import org.cats.onlinebank.di.ProvideAppModules
import org.cats.onlinebank.ui.theme.OnlineBankTheme
import org.koin.compose.KoinApplication

fun mainViewController() = ComposeUIViewController {
    App()
}

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(ProvideAppModules.getAppModules())
        }
    ) {
        OnlineBankTheme {
            val navController: NavHostController = rememberNavController()

            NavGraph(
                navController = navController,
                navActions = remember(navController) {
                    NavigationHelpers(
                        navController
                    )
                },
            )
        }
    }
}
