package com.kursatkumsuz.signup.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.domain.model.User
import com.kursatkumsuz.signup.domain.usecase.SignUpUseCases
import com.kursatkumsuz.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: SignUpUseCases
) : ViewModel() {


    var loadingState by mutableStateOf(false)
        private set

    private fun setLoading(isLoading: Boolean) {
        loadingState = isLoading
    }

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.signUpWithEmailAndPasswordUseCase.invoke(email, password)
            .onEach { result ->
                when (result) {
                    is Response.Success -> {
                        onSuccess()
                        setLoading(isLoading = false)
                    }

                    is Response.Loading -> {
                        setLoading(isLoading = true)
                    }

                    is Response.Error -> {
                        onError(result.errorMessage)
                        setLoading(isLoading = false)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun saveUser(
        name: String,
        email: String,
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
        useCase.saveUserUseCase.invoke(user = User(name = name, email = email))
            .onEach { response ->
                when (response) {
                    is Response.Success -> {
                        onSuccess()
                    }

                    is Response.Error -> {
                        onError(response.errorMessage)
                    }

                    else -> {
                    }
                }
            }.launchIn(viewModelScope)
    }
}


