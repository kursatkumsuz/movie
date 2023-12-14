package com.kursatkumsuz.signin.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kursatkumsuz.component.AuthButton
import com.kursatkumsuz.component.AuthInputText
import com.kursatkumsuz.component.AuthPasswordText
import kotlinx.coroutines.delay


@Composable
fun SignInContent(
    isLoading: Boolean,
    onSignInClick: (String, String) -> Unit,
    onNavigateSignUp: () -> Unit,
    onNavigateHome: () -> Unit,
    isAuthenticated: Boolean
) {
    var emailState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }
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
        Spacer(modifier = Modifier.height(50.dp))
        AuthButton(
            title = "Sign In",
            loading = isLoading,
            success = isAuthenticated,
            onClick = { onSignInClick(emailState, passwordState) }
        )

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

    LaunchedEffect(key1 = isAuthenticated) {
        if (isAuthenticated) {
            delay(2000)
            onNavigateHome()
        }
    }

}