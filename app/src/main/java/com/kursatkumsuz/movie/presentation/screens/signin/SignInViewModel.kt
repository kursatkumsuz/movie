package com.kursatkumsuz.movie.presentation.screens.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.movie.domain.usecase.UseCases
import com.kursatkumsuz.movie.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCase: UseCases
) : ViewModel() {

    var loadingState by mutableStateOf(false)
        private set

    var authenticateState by mutableStateOf(false)
        private set

    private fun setLoading(isLoading: Boolean) {
        loadingState = isLoading
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.signInWithEmailAndPasswordUseCase.invoke(email, password)
            .onEach { result ->
                when (result) {
                    is Response.Success -> {
                        setLoading(isLoading = false)
                        onSuccess()
                        delay(1000)
                        authenticateState = true
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