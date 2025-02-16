package rrff.fast_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rrff.fast_shop.navigation.Screen
import rrff.fast_shop.ui.LoginScreen
import rrff.fast_shop.ui.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Cria o NavController
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route // Define a tela inicial
            ) {
                composable(Screen.Login.route) { // Tela de login
                    LoginScreen(navController)
                }
                composable(Screen.Home.route) { // Tela inicial (home)
                    HomeScreen()
                }
            }

        }
    }
}