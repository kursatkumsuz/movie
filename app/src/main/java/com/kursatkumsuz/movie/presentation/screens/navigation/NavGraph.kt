package com.kursatkumsuz.movie.presentation.screens.navigation

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
import com.kursatkumsuz.movie.presentation.screens.auth.AuthenticationScreen
import com.kursatkumsuz.movie.presentation.screens.detail.DetailScreen
import com.kursatkumsuz.movie.presentation.screens.home.HomeScreen
import com.kursatkumsuz.movie.presentation.screens.onboarding.OnBoardingScreen
import com.kursatkumsuz.movie.presentation.screens.onboarding.OnBoardingViewModel
import com.kursatkumsuz.movie.presentation.screens.profile.ProfileScreen
import com.kursatkumsuz.movie.presentation.screens.search.SearchScreen
import com.kursatkumsuz.movie.presentation.screens.signin.SignInScreen
import com.kursatkumsuz.movie.presentation.screens.signup.SignUpScreen
import com.kursatkumsuz.movie.presentation.screens.splash.SplashScreen
import com.kursatkumsuz.movie.presentation.screens.video.VideoScreen
import com.kursatkumsuz.movie.presentation.screens.watchlist.WatchListScreen
import com.kursatkumsuz.movie.util.Constants.MOVIE_ID
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
fun NavGraphBuilder.authenticationScreen(navController: NavController) {
    composable(route = Screen.AuthenticationScreen.route) {
        AuthenticationScreen(
            onNavigateSignInClick = { navController.navigate(Screen.SignInScreen.route) },
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signInScreen(navController: NavController) {
    composable(Screen.SignInScreen.route) {
        SignInScreen(
            onNavigateSignUp = { navController.navigate(Screen.SignUpScreen.route) },
            onNavigateHome = { navController.navigate(Screen.HomeScreen.route) },
            onNavigateForgotPassword = {}
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signUpScreen(navController: NavController) {

    composable(Screen.SignUpScreen.route) {
        SignUpScreen(
            onNavigateSignIn = { navController.navigate(Screen.SignInScreen.route) }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeScreen(navController: NavController) {

    composable(Screen.HomeScreen.route) {
        HomeScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.detailScreen(navController: NavController) {

    composable(Screen.DetailScreen.route + "/{${MOVIE_ID}}") {
        DetailScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.videoScreen(navController: NavController) {

    composable(Screen.VideoScreen.route + "/{${MOVIE_ID}}") {
        VideoScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchScreen(navController: NavController) {

    composable(Screen.SearchScreen.route) {
        SearchScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.watchList(navController: NavController) {

    composable(Screen.WatchListScreen.route) {
        WatchListScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profile(navController: NavController) {

    composable(Screen.ProfileScreen.route) {
        ProfileScreen(navController = navController)
    }
}