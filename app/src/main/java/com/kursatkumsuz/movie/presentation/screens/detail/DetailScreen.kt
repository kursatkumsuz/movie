package com.kursatkumsuz.movie.presentation.screens.detail


import androidx.compose.runtime.Composable

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie
import com.kursatkumsuz.movie.util.Screen

@Composable
fun DetailScreen(navController: NavController) {
    val viewModel: DetailViewModel = hiltViewModel()
    val state = viewModel.state.value
    val watchListMovie = state.movie?.let {
        WatchListMovie(
            movieId = it.id,
            title = it.title,
            poster = it.backDropPath
        )
    }
    val castList = viewModel.creditList.value

    DetailContent(
        movie = state.movie,
        isLoading = state.isLoading,
        cast = castList,
        navController = navController,
        onSaveWatchlistClick = {
            if (watchListMovie != null) {
                viewModel.saveToWatchlist(movie = watchListMovie)
            }
        },
        onWatchClick = {
            navController.popBackStack()
            navController.navigate(Screen.VideoScreen.route + "/${state.movie?.id}")
        }
    )

}