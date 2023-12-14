package com.kursatkumsuz.home.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun HomeScreen(onNavigateDetailScreen : (String) -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val image = viewModel.imageState.value
    val user = viewModel.userState.value
    val topRatedPagingItem = viewModel.topRatedMovieList.collectAsLazyPagingItems()
    val popularPagingItem = viewModel.popularMovieList.collectAsLazyPagingItems()
    val nowPlayingPagingItem = viewModel.nowPlayingMovieList.collectAsLazyPagingItems()

    HomeContent(
        profileImage = image,
        user = user,
        topRatedPagingItem = topRatedPagingItem,
        popularPagingItem = popularPagingItem,
        nowPlayingPagingItem = nowPlayingPagingItem,
        onNavigateDetailScreen = onNavigateDetailScreen
    )
}
