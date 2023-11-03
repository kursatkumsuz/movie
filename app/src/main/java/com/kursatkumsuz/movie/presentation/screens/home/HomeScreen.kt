package com.kursatkumsuz.movie.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()

    val topRatedPagingItem = viewModel.topRatedMovieList.collectAsLazyPagingItems()
    val popularPagingItem = viewModel.popularMovieList.collectAsLazyPagingItems()
    val nowPlayingPagingItem = viewModel.nowPlayingMovieList.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeContent(
            topRatedPagingItem = topRatedPagingItem,
            popularPagingItem = popularPagingItem,
            nowPlayingPagingItem = nowPlayingPagingItem
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}