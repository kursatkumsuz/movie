package com.kursatkumsuz.movie.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kursatkumsuz.movie.util.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    val viewModel: SplashViewModel = hiltViewModel()
    val onBoardingState = viewModel.onBoardingState
    val signedInState = viewModel.onBoardingState
    SplashContent()

    LaunchedEffect(key1 = onBoardingState) {
        delay(1000)
        if (onBoardingState && signedInState) {
            navController.navigate(Screen.HomeScreen.route)
        } else if (onBoardingState) {
            navController.navigate(Screen.AuthenticationScreen.route)
        } else {
            navController.navigate(Screen.OnBoardingScreen.route)
        }
    }
}