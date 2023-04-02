package com.kursatkumsuz.movie.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = "splash_screen")
    object OnBoardingScreen : Screen(route = "onboarding_screen")
    object AuthenticationScreen : Screen(route = "authentication_screen")
    object SignInScreen : Screen("sign_in_screen")
    object SignUpScreen : Screen("sign_up_screen")

    object HomeScreen : Screen("home_screen")

}
