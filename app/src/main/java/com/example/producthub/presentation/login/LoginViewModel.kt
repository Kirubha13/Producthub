package com.example.producthub.presentation.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.producthub.core.datastore.SessionManager
import com.example.producthub.domain.useCase.SessionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionUseCases: SessionUseCases
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun login(
        onSuccess: () -> Unit
    ) {

        val email = uiState.email.trim()
        val password = uiState.password

        when {

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                uiState = uiState.copy(
                    emailError = "Invalid email"
                )
            }

            password.length < 8 -> {
                uiState = uiState.copy(
                    passwordError = "Minimum 8 characters required"
                )
            }

            else -> {

                viewModelScope.launch {

                    sessionUseCases.login(email)
                    delay(100)
                    onSuccess()
                }
            }
        }
    }
}