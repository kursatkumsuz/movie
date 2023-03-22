package com.kursatkumsuz.movie.presentation.screens.splash

import androidx.compose.runtime.mutableStateOf
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

    private var _onBoardingState = mutableStateOf(false)
    val onBoardingState = _onBoardingState

    init {
        getOnBoardingState()
    }

    fun getOnBoardingState() {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingState.value = useCases.readOnBoardingStateUseCase().stateIn(viewModelScope).value
        }
    }
}