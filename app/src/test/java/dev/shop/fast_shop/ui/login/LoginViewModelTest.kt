package dev.shop.fast_shop.ui.login

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*
import shop.fast_shop.service.AuthService

class LoginViewModelTest {

    private val authService = mock(AuthService::class.java)
    private val viewModel = LoginViewModel(authService)

    @Test
    fun testEmailChange() {
        val email = "test@example.com"
        viewModel.onEmailChange(email)
        assertEquals(email, viewModel.state.email)
    }

    @Test
    fun testPasswordChange() {
        val password = "password123"
        viewModel.onPasswordChange(password)
        assertEquals(password, viewModel.state.password)
    }
}