package dev.shop.fast_shop.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("SignUp")
    object Home : Screen("HomeScreen")

}