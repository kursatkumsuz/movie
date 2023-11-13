package com.kursatkumsuz.movie.presentation.screens.watchlist

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun WatchListScreen(navController: NavController) {
    val viewModel: WatchListViewModel = hiltViewModel()
    val state = viewModel.state.value

    WatchListContent(movie = state.movie, navController = navController)
}