package com.kursatkumsuz.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kursatkumsuz.auth.navigation.authenticationScreen
import com.kursatkumsuz.detail.presentation.navigation.detailScreen
import com.kursatkumsuz.home.presentation.navigation.homeScreen
import com.kursatkumsuz.onboarding.presentation.navigation.onBoardingScreen
import com.kursatkumsuz.profile.presentation.navigation.profile
import com.kursatkumsuz.search.presentation.navigation.searchScreen
import com.kursatkumsuz.signin.presentation.navigation.signInScreen
import com.kursatkumsuz.signup.presentation.navigation.signUpScreen
import com.kursatkumsuz.splash.presentation.navigation.splashScreen
import com.kursatkumsuz.util.Screen
import com.kursatkumsuz.video.presentation.navigation.videoScreen
import com.kursatkumsuz.watchlist.presentation.navigation.watchList

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        splashScreen(navController = navController)
        onBoardingScreen(navController = navController)
        authenticationScreen(navController = navController)
        signInScreen(navController = navController)
        signUpScreen(navController = navController)
        homeScreen(navController = navController)
        detailScreen(navController = navController)
        videoScreen(navController = navController)
        watchList(navController = navController)
        searchScreen(navController = navController)
        profile(navController = navController)
    }
}

