package com.example.emlakappfirebase.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.emlakappfirebase.koin.di.repos.LoginRepository
import com.example.emlakappfirebase.ui.screens.logic.LoginUIState
import com.example.emlakappfirebase.ui.screens.logic.Validator
import com.example.emlakappfirebase.viewmodels.events.LoginUIEvent

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        loginRepository.login(email, password) { success, errorMessage ->
            loginInProgress.value = false
            if (success) {
                Log.d(TAG, "Login successful")
            } else {
                Log.d(TAG, "Login failed: $errorMessage")
            }
        }
    }
}

