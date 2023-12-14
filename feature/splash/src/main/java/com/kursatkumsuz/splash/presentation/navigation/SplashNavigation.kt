package com.kursatkumsuz.splash.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.splash.presentation.SplashScreen
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.splashScreen(navController: NavController) {

    composable(route = Screen.SplashScreen.route) {
        SplashScreen(
            onNavigateAuthenticationScreen = {
                navController.popBackStack()
                navController.navigate(Screen.AuthenticationScreen.route)
            },
            onNavigateHomeScreen = {
                navController.popBackStack()
                navController.navigate(Screen.HomeScreen.route)
            },
            onNavigateOnBoardingScreen = {
                navController.popBackStack()
                navController.navigate(Screen.OnBoardingScreen.route)
            }
        )
    }
}