package com.kursatkumsuz.video.presentation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.component.LoadingPage

@Composable
fun VideoScreen(onNavigateHome: () -> Unit) {
    val viewModel: VideoViewModel = hiltViewModel()
    val state = viewModel.state.value

    if (state.isLoading) {
        LoadingPage()
    } else {
        state.movie?.let {
            VideoContent(
                video = it,
                onBackClick = onNavigateHome
            )
        }
    }
}

