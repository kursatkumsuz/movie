package com.kursatkumsuz.movie.presentation.screens.signup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.movie.domain.usecase.UseCases
import com.kursatkumsuz.movie.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: UseCases
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
}

