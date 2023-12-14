package com.kursatkumsuz.search.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.searchScreen(navController: NavController) {
    composable(Screen.SearchScreen.route) {
        com.kursatkumsuz.search.presentation.SearchScreen(
            onNavigateDetailScreen = { id ->
                navController.popBackStack()
                navController.navigate(Screen.DetailScreen.route + "/${id}")
            }
        )
    }
}