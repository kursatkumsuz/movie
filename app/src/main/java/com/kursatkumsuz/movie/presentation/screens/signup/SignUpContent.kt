package com.kursatkumsuz.movie.presentation.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kursatkumsuz.movie.presentation.component.AuthInputText
import com.kursatkumsuz.movie.presentation.component.AuthPasswordText

@Composable
fun SignUpContent(
    onSignUpClick: () -> Unit,
    onNavigateSignInClick: () -> Unit,
) {
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

        AuthInputText("Name", onInput = {})

        Spacer(modifier = Modifier.height(20.dp))

        AuthInputText("E-mail", onInput = {})

        Spacer(modifier = Modifier.height(20.dp))

        AuthPasswordText(onInput = {})

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
            onClick = onSignUpClick
        ) {
            Text(text = "Sign Up", color = Color.White)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Have you an account?",
                fontSize = 14.sp,
                color = Color.LightGray
            )
            TextButton(
                onClick = onNavigateSignInClick
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