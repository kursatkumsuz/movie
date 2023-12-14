package com.kursatkumsuz.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateHomeScreen: () -> Unit,
    onNavigateAuthenticationScreen: () -> Unit,
    onNavigateOnBoardingScreen: () -> Unit
) {

    val viewModel: SplashViewModel = hiltViewModel()
    val onBoardingState = viewModel.onBoardingState
    val signedInState = viewModel.signedInState
    SplashContent()

    LaunchedEffect(key1 = onBoardingState) {
        delay(1000)
        if (onBoardingState && signedInState) {
            onNavigateHomeScreen()
        } else if (onBoardingState) {
            onNavigateAuthenticationScreen()
        } else {
            onNavigateOnBoardingScreen()
        }
    }
}