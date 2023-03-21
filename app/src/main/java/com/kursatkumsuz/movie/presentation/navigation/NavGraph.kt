package com.kursatkumsuz.movie.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.kursatkumsuz.movie.presentation.onboarding.OnBoardingScreen
import com.kursatkumsuz.movie.presentation.onboarding.OnBoardingViewModel
import com.kursatkumsuz.movie.presentation.splash.SplashScreen
import com.kursatkumsuz.movie.util.Screen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        splashScreen(navController = navController)
        onBoardingRoute(navController = navController)
        authenticationScreen()
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashScreen(navController: NavController) {
    composable(route = Screen.SplashScreen.route) {
        SplashScreen(navController = navController)
    }
}

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalPagerApi::class
)
fun NavGraphBuilder.onBoardingRoute(navController: NavController) {

    composable(route = Screen.OnBoardingScreen.route) {
        val pagerState = rememberPagerState()
        val viewModel: OnBoardingViewModel = hiltViewModel()

        OnBoardingScreen(
            pagerState = pagerState,
            onStartClick = {
                viewModel.saveOnBoardingState()
                navController.navigate(Screen.AuthenticationScreen.route)
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authenticationScreen() {
    composable(route = Screen.AuthenticationScreen.route) {

    }
}