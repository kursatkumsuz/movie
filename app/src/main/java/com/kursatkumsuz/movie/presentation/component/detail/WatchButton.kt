package com.kursatkumsuz.movie.presentation.component.detail


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WatchButton(onWatchClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.clip(CircleShape).size(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4761B5)
            ),
            onClick = onWatchClick
        ) {
            Icon(
                modifier = Modifier.size(90.dp),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Watch Video Icon",
                tint = Color.White
            )
        }
    }
}