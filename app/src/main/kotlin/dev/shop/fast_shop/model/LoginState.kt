package dev.shop.fast_shop.model

data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val uidUser: String = "",
    val showError: Boolean = false
)