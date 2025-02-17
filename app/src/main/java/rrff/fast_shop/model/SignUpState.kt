package rrff.fast_shop.model

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisible: Boolean = false,
    val showError: Boolean = false,
    val errorMessage: String = ""
)