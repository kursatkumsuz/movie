package com.kursatkumsuz.auth

import androidx.compose.runtime.Composable

@Composable
fun AuthenticationScreen(
    onNavigateSignInClick: () -> Unit,
) {
    AuthenticationContent(
        onNavigateSignInClick = onNavigateSignInClick,
    )
}