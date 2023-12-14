package com.kursatkumsuz.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.home.presentation.HomeScreen
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(Screen.HomeScreen.route) {
        HomeScreen(
            onNavigateDetailScreen = { id ->
                navController.navigate(Screen.DetailScreen.route + "/${id}")
            }
        )
    }
}