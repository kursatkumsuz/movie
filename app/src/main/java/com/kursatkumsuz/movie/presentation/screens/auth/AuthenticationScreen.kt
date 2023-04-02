package com.kursatkumsuz.movie.presentation.screens.auth

import androidx.compose.runtime.Composable

@Composable
fun AuthenticationScreen(
    onNavigateSignInClick: () -> Unit,
    onSignInWithGoogle: () -> Unit
) {
    AuthenticationContent(
        onNavigateSignInClick = onNavigateSignInClick,
        onSignInWithGoogle = onSignInWithGoogle
    )
}