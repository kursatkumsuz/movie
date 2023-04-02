package com.kursatkumsuz.movie.presentation.screens.splash

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.movie.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
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
        viewModelScope.launch(Dispatchers.IO) {
            onBoardingState = useCases.readOnBoardingStateUseCase().stateIn(viewModelScope).value
        }
    }

    private fun getSignedInState() {
        viewModelScope.launch(Dispatchers.IO) {
            signedInState = useCases.readOnBoardingStateUseCase().stateIn(viewModelScope).value
        }
    }
}