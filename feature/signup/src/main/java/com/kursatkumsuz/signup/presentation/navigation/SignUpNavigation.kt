package com.kursatkumsuz.signup.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kursatkumsuz.signup.presentation.SignUpScreen
import com.kursatkumsuz.util.Screen

fun NavGraphBuilder.signUpScreen(navController: NavController) {
    composable(Screen.SignUpScreen.route) {
        SignUpScreen(
            onNavigateSignIn = { navController.navigate(Screen.SignInScreen.route) }
        )
    }
}