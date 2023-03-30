package com.kursatkumsuz.movie.presentation.screens.signup

import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onNavigateSignInClick: () -> Unit,
) {
    SignUpContent(
        onSignUpClick = onSignUpClick,
        onNavigateSignInClick = onNavigateSignInClick
    )
}