package com.kursatkumsuz.watchlist.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.watchList(navController: NavController) {
    composable(Screen.WatchListScreen.route) {
        com.kursatkumsuz.watchlist.presentation.WatchListScreen(
            onNavigateDetail = { id ->
                navController.popBackStack()
                navController.navigate(Screen.DetailScreen.route + "/${id}")
            }
        )
    }
}