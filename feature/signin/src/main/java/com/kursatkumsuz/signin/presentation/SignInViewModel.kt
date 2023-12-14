package com.kursatkumsuz.signin.presentation

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.signin.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.kursatkumsuz.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase
) : ViewModel() {

    var state by mutableStateOf<SignInUIState>(SignInUIState())
        private set

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        signInWithEmailAndPasswordUseCase.invoke(email, password)
            .onEach { result ->
                state = when (result) {
                    is Response.Success -> {
                        onSuccess()
                        SignInUIState(isAuthenticated = true)
                    }

                    is Response.Loading -> {
                        SignInUIState(isLoading = true)
                    }

                    is Response.Error -> {
                        onError(result.errorMessage)
                        SignInUIState()
                    }
                }
            }.launchIn(viewModelScope)
    }
}