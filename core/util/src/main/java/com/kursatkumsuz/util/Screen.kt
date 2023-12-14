package com.kursatkumsuz.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = "splash_screen")
    object OnBoardingScreen : Screen(route = "onboarding_screen")
    object AuthenticationScreen : Screen(route = "authentication_screen")
    object SignInScreen : Screen("sign_in_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("detail_screen")
    object VideoScreen : Screen("video_screen")
    object SearchScreen : Screen("search_screen")
    object WatchListScreen : Screen("watchlist_screen")
    object ProfileScreen : Screen("profile_screen")

}
