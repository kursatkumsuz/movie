package com.kursatkumsuz.movie.presentation.screens.video


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kursatkumsuz.movie.presentation.component.PageLoader
import com.kursatkumsuz.movie.util.Screen

@Composable
fun VideoScreen(navController: NavController) {
    val viewModel: VideoViewModel = hiltViewModel()
    val state = viewModel.state.value

    if (state.isLoading) {
        PageLoader()
    } else {
        state.movie?.let {
            VideoContent(
                video = it,
                onBackClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.HomeScreen.route)
                }
            )
        }
    }
}

