package com.kursatkumsuz.movie.presentation.screens.signin

import androidx.compose.runtime.Composable

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
    onNavigateSignUpClick: () -> Unit,
    onNavigateForgotPassword: () -> Unit
) {

    SignInContent(
        onSignInClick = onSignInClick,
        onNavigateSignUpClick = onNavigateSignUpClick,
        onNavigateForgotPassword = onNavigateForgotPassword
    )
}
