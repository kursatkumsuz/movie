package com.kursatkumsuz.movie.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kursatkumsuz.movie.R
import com.kursatkumsuz.movie.presentation.component.SlidingBackgroundImage

@Composable
fun AuthenticationContent(
    onNavigateSignInClick: () -> Unit,
    onSignInWithGoogle: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SlidingBackgroundImage()
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF4761B5),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15),
                onClick = onNavigateSignInClick
            ) {
                Text(text = "Sign In")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0x4F4761B5),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15),
                onClick = onSignInWithGoogle
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Logo",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Sign In With Google")
            }
        }
    }
}