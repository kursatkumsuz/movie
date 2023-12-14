package com.kursatkumsuz.onboarding.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.onboarding.presentation.OnBoardingScreen
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.onBoardingScreen(navController: NavController) {
    composable(route = Screen.OnBoardingScreen.route) {
        OnBoardingScreen(
            onNavigateAuthenticationScreen = { navController.navigate(Screen.AuthenticationScreen.route) }
        )
    }
}