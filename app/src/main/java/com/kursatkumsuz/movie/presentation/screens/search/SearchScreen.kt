package com.kursatkumsuz.movie.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kursatkumsuz.movie.presentation.screens.search.component.SearchBar
import com.kursatkumsuz.movie.presentation.screens.search.component.SearchList
import com.kursatkumsuz.movie.util.Screen

@Composable
fun SearchScreen(navController: NavController) {
    val viewModel: SearchViewModel = hiltViewModel()
    val pagingItem = viewModel.searchedMovieList.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SearchBar(onSearch = viewModel::onSearchEvent)

        SearchList(
            pagingItem = pagingItem,
            onNavigateDetailScreen = { id -> navController.navigate(Screen.DetailScreen.route + "/${id}") })
    }
}

