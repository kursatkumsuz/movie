package com.kursatkumsuz.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kursatkumsuz.auth.component.SlidingBackgroundImage

@Composable
fun AuthenticationContent(
    onNavigateSignInClick: () -> Unit, ) {
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
                    containerColor = Color(0x4F4761B5),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15),
                onClick = onNavigateSignInClick
            ) {
                Text(text = "Sign In")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}