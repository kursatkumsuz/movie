package com.kursatkumsuz.video.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.util.Constants.MOVIE_ID
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.videoScreen(navController: NavController) {
    composable(
        route = Screen.VideoScreen.route + "/{${MOVIE_ID}}",
    ) {
        com.kursatkumsuz.video.presentation.VideoScreen(
            onNavigateHome = {
                navController.popBackStack()
                navController.navigate(Screen.HomeScreen.route)
            }
        )
    }
}