package rrff.fast_shop.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import rrff.fast_shop.model.LoginState
import rrff.fast_shop.service.AuthService

class LoginViewModel(
    private val authService: AuthService = AuthService()
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state = state.copy(password = password)
    }

    fun onTogglePasswordVisibility() {
        state = state.copy(passwordVisible = !state.passwordVisible)
    }

    fun login(onSuccess: () -> Unit) {
        if (state.email.isEmpty() || state.password.isEmpty()) {
            state = state.copy(showError = true)
            return
        }

        authService.login(
            email = state.email,
            password = state.password,
            onSuccess = {
                state = state.copy(showError = false)
                onSuccess()
            },
            onFailure = {
                state = state.copy(showError = true)
            }
        )
    }
}