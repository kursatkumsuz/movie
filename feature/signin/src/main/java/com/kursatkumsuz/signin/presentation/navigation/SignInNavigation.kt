package com.kursatkumsuz.signin.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.signin.presentation.SignInScreen
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.signInScreen(navController: NavController) {
    composable(Screen.SignInScreen.route) {
       SignInScreen(
            onNavigateSignUp = { navController.navigate(Screen.SignUpScreen.route) },
            onNavigateHome = { navController.navigate(Screen.HomeScreen.route) },
        )
    }
}