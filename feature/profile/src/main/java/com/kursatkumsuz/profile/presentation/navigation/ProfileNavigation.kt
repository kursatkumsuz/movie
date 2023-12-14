package com.kursatkumsuz.profile.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.profile.presentation.ProfileScreen
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.profile(navController: NavController) {
    composable(Screen.ProfileScreen.route) {
        ProfileScreen(
            onNavigateAuthentication = {
                navController.popBackStack()
                navController.navigate(Screen.AuthenticationScreen.route)
            })
    }
}