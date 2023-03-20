package com.kursatkumsuz.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kursatkumsuz.movie.ui.theme.MovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MovieTheme {
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieTheme {
    }
}