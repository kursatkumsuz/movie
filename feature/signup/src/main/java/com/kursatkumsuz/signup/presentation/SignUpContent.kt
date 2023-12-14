package com.kursatkumsuz.signup.presentation

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

@Composable
fun SignUpContent(isLoading: Boolean, isAuthenticated : Boolean, onSignUpClick: (String, String, String) -> Unit, onNavigateSignIn : () -> Unit) {

    var emailState by remember { mutableStateOf("") }
    var nameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Sign Up",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Please fill the all informations",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(80.dp))

        AuthInputText(labelText = "Name", onInput = { name ->
            nameState = name
        })

        Spacer(modifier = Modifier.height(20.dp))

        AuthInputText(labelText = "E-mail", onInput = { mail ->
            emailState = mail
        })

        Spacer(modifier = Modifier.height(20.dp))

        AuthPasswordText(onInput = { password ->
            passwordState = password
        })

        Spacer(modifier = Modifier.height(50.dp))
        AuthButton( title = "Sign Up", onClick = {onSignUpClick(emailState, passwordState, nameState)}, loading = isLoading, success = isAuthenticated)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Have you an account?",
                fontSize = 14.sp,
                color = Color.LightGray
            )
            TextButton(
                onClick = onNavigateSignIn
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 14.sp,
                    color = Color.Blue
                )
            }
        }
    }
}