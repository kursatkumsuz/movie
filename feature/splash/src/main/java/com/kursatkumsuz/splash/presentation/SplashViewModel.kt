package com.kursatkumsuz.splash.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.splash.domain.usecase.SplashUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashUseCases: SplashUseCases
) : ViewModel() {

    var onBoardingState by mutableStateOf(false)
        private set

    var signedInState by mutableStateOf(false)
        private set

    init {
        getOnBoardingState()
        getSignedInState()
    }

    private fun getOnBoardingState() {
        viewModelScope.launch {
            onBoardingState = splashUseCases.readOnBoardingStateUseCase().stateIn(viewModelScope).value
        }
    }

    private fun getSignedInState() {
        viewModelScope.launch {
            signedInState = splashUseCases.isSignedInUseCase().stateIn(viewModelScope).value
        }
    }
}