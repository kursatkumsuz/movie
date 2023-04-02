package com.kursatkumsuz.movie.presentation.screens.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.messagebar.BarType
import com.kursatkumsuz.messagebar.MessageBarContent
import com.kursatkumsuz.messagebar.rememberAnimatedBarState
import com.kursatkumsuz.movie.presentation.component.AuthInputText
import com.kursatkumsuz.movie.presentation.component.AuthPasswordText
import com.kursatkumsuz.movie.presentation.component.CircularProgress

@Composable
fun SignInScreen(
    onNavigateSignUp: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateForgotPassword: () -> Unit
) {
    val viewModel: SignInViewModel = hiltViewModel()

    val loadingState = viewModel.loadingState

    val authenticateState = viewModel.authenticateState

    var emailState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }

    val barState = rememberAnimatedBarState()

    MessageBarContent(barState = barState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Sign In",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Sign in with your account",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(80.dp))

            AuthInputText(
                labelText = "E-mail",
                onInput = { mail ->
                    emailState = mail
                })

            Spacer(modifier = Modifier.height(20.dp))

            AuthPasswordText(
                onInput = { password ->
                    passwordState = password
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 30.dp),
                onClick = onNavigateForgotPassword
            ) {
                Text(
                    text = "Forgot Password",
                    fontSize = 14.sp,
                    color = Color.Blue
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF4761B5),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15),
                onClick = {
                    viewModel.signInWithEmailAndPassword(
                        email = emailState,
                        password = passwordState,
                        onSuccess = {
                            barState.AnimatedMessageBar(
                                message = "Successfully Signed In!",
                                type = BarType.SUCCESS
                            )
                        },
                        onError = { msg ->
                            barState.AnimatedMessageBar(
                                message = msg,
                                type = BarType.ERROR
                            )
                        }
                    )
                }
            ) {
                Text(text = "Sign In", color = Color.White)
                Spacer(modifier = Modifier.width(5.dp))
                CircularProgress(isVisible = loadingState)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Don't you have an account?",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
                TextButton(
                    onClick = onNavigateSignUp
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 14.sp,
                        color = Color.Blue
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = authenticateState) {
        if (authenticateState)
            onNavigateHome()
    }
}
