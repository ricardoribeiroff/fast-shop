package shop.fast_shop.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import shop.fast_shop.model.SignUpState
import shop.fast_shop.service.AuthService

class SignUpViewModel(
    private val authService: AuthService = AuthService()
) : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    fun onEmailChange(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state = state.copy(password = password)
    }
    fun onConfirmPasswordChange(password: String) {
        state = state.copy(confirmPassword = password)
    }


    fun matchingPassword(): Boolean {
        return when {
            state.password.isEmpty() || state.confirmPassword.isEmpty() -> {
                state = state.copy(
                    showError = true,
                    errorMessage = "As senhas não podem estar vazias"
                )
                false
            }
            state.password.length < 6 -> {
                state = state.copy(
                    showError = true,
                    errorMessage = "A senha deve ter pelo menos 6 caracteres"
                )
                false
            }
            state.password != state.confirmPassword -> {
                state = state.copy(
                    showError = true,
                    errorMessage = "As senhas não conferem"
                )
                false
            }
            else -> {
                state = state.copy(
                    showError = false,
                    errorMessage = ""
                )
                true
            }
        }
    }

    fun register(onSuccess: () -> Unit) {
        if (state.email.isEmpty() || state.password.isEmpty()) {
            state = state.copy(showError = true)
            state = state.copy(errorMessage = "Preencha todos os campos")
            return
        }

        authService.register(
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