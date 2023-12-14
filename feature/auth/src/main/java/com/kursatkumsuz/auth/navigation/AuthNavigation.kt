package com.kursatkumsuz.auth.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.auth.AuthenticationScreen
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.authenticationScreen(navController: NavController) {
    composable(route = Screen.AuthenticationScreen.route) {
       AuthenticationScreen(
            onNavigateSignInClick = { navController.navigate(Screen.SignInScreen.route) },
        )
    }
}