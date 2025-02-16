package rrff.fast_shop.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("HomeScreen")
}