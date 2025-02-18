package shop.fast_shop.model

data class LoginState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val showError: Boolean = false
)