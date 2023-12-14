package com.kursatkumsuz.watchlist.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.messagebar.BarType
import com.kursatkumsuz.messagebar.MessageBarContent
import com.kursatkumsuz.messagebar.rememberAnimatedBarState

@Composable
fun WatchListScreen(onNavigateDetail: (String) -> Unit) {
    val viewModel: WatchListViewModel = hiltViewModel()
    val state = viewModel.state.value
    val barState = rememberAnimatedBarState()

    MessageBarContent(barState = barState, duration = 800L) {
        WatchListContent(
            movie = state.movie,
            onDelete = { id ->
                viewModel.deleteWatchList(
                    movieId = id,
                    onSuccess = {
                        barState.AnimatedMessageBar(
                            message = "Successfully Deleted!",
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
            },
            onNavigateDetail = onNavigateDetail
        )
    }
}