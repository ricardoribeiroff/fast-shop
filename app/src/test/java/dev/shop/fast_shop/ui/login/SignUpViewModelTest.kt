package dev.shop.fast_shop.ui.signup

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*
import shop.fast_shop.service.AuthService
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer


object MockitoHelper {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T = null as T
}

class SignUpViewModelTest {

    private val authService = mock(AuthService::class.java)
    private val viewModel = SignUpViewModel(authService)

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

    @Test
    fun testConfirmPasswordChange() {
        val confirmPassword = "password123"
        viewModel.onConfirmPasswordChange(confirmPassword)
        assertEquals(confirmPassword, viewModel.state.confirmPassword)
    }

    @Test
    fun testMatchingPassword() {
        viewModel.onPasswordChange("password123")
        viewModel.onConfirmPasswordChange("password123")
        assertTrue(viewModel.matchingPassword())
    }

    @Test
    fun testRegisterSuccess() {
        `when`(authService.register(anyString(), anyString(), MockitoHelper.anyObject(), MockitoHelper.anyObject())).thenAnswer {
            (it.arguments[2] as () -> Unit).invoke()
        }

        viewModel.onEmailChange("test@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.onConfirmPasswordChange("password123")

        var successCalled = false
        viewModel.register { successCalled = true }

        assertFalse(viewModel.state.showError)
        assertTrue(successCalled)
    }

    @Test
    fun testRegisterFailure() {
        doAnswer {
            (it.arguments[3] as Runnable).run()
        }.`when`(authService).register(anyString(), anyString(), MockitoHelper.anyObject(), MockitoHelper.anyObject())

        viewModel.onEmailChange("test@example.com")
        viewModel.onPasswordChange("password123")
        viewModel.onConfirmPasswordChange("password123")

        viewModel.register {}

        assertTrue(viewModel.state.showError)
    }
}