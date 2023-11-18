package com.kursatkumsuz.movie.presentation.screens.detail


import androidx.compose.runtime.Composable

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kursatkumsuz.messagebar.BarType
import com.kursatkumsuz.messagebar.MessageBarContent
import com.kursatkumsuz.messagebar.rememberAnimatedBarState
import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie
import com.kursatkumsuz.movie.util.Screen

@Composable
fun DetailScreen(navController: NavController) {
    val viewModel: DetailViewModel = hiltViewModel()
    val barState = rememberAnimatedBarState()
    val state = viewModel.state.value
    val watchListMovie = state.movie?.let {
        WatchListMovie(
            movieId = it.id,
            title = it.title,
            poster = it.backDropPath
        )
    }
    val castList = viewModel.creditList.value
    MessageBarContent(barState = barState) {
        DetailContent(
            movie = state.movie,
            isLoading = state.isLoading,
            cast = castList,
            onBackClick = {
                navController.navigate(Screen.HomeScreen.route)
            },
            onSaveWatchlistClick = {
                if (watchListMovie != null) {
                    viewModel.saveToWatchlist(
                        movie = watchListMovie,
                        onSuccess = {
                            barState.AnimatedMessageBar(
                                message = "Successfully Added!",
                                type = BarType.SUCCESS
                            )
                        },
                        onError = { msg ->
                            barState.AnimatedMessageBar(
                                message = msg,
                                type = BarType.ERROR
                            )
                        }
                    )
                }
            },
            onWatchClick = {
                navController.popBackStack()
                navController.navigate(Screen.VideoScreen.route + "/${state.movie?.id}")
            }
        )
    }
}