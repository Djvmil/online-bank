package org.cats.onlinebank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.cats.onlinebank.feature.navigation.NavGraph
import org.cats.onlinebank.feature.navigation.NavigationHelpers
import org.cats.onlinebank.ui.theme.OnlineBankTheme
import org.koin.dsl.koinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            koinApplication()
            OnlineBankTheme {
                val navController: NavHostController = rememberNavController()

                NavGraph(
                    navController = navController,
                    navActions = remember(navController) { NavigationHelpers(navController) },
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OnlineBankTheme {
    }
}