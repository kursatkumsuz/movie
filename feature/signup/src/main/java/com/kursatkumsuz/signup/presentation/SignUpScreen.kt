package com.kursatkumsuz.signup.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.messagebar.BarType
import com.kursatkumsuz.messagebar.MessageBarContent
import com.kursatkumsuz.messagebar.rememberAnimatedBarState

@Composable
fun SignUpScreen(
    onNavigateSignIn: () -> Unit
) {
    val viewModel: SignUpViewModel = hiltViewModel()

    var isSignUp by remember { mutableStateOf(false) }
    var isUserSaved by remember { mutableStateOf(false) }

    val loadingState = viewModel.loadingState
    val barState = rememberAnimatedBarState()

    if (isSignUp && isUserSaved)
        LaunchedEffect(key1 = Unit) {
            onNavigateSignIn()
        }

    MessageBarContent(barState = barState) {
        SignUpContent(
            isLoading = loadingState,
            isAuthenticated = false,
            onNavigateSignIn = onNavigateSignIn,
            onSignUpClick = { mail, password, name ->
                viewModel.signUpWithEmailAndPassword(
                    email = mail,
                    password = password,
                    onSuccess = { isSignUp = true },
                    onError = { msg ->
                        barState.AnimatedMessageBar(message = msg, BarType.ERROR)
                    }
                )
                viewModel.saveUser(
                    name = name,
                    email = mail,
                    onSuccess = { isUserSaved = true },
                    onError = { msg ->
                        barState.AnimatedMessageBar(
                            message = msg,
                            BarType.ERROR
                        )
                    })
            })
    }
}