package com.kursatkumsuz.movie.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = "splash_screen")
    object OnBoardingScreen : Screen(route = "onboarding_screen")
    object AuthenticationScreen : Screen(route = "authentication_screen")
}
