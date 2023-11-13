package com.kursatkumsuz.movie.presentation.screens.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.kursatkumsuz.movie.data.model.search.SearchResult
import com.kursatkumsuz.movie.presentation.component.home.ErrorDialog
import com.kursatkumsuz.movie.presentation.screens.home.PageLoader
import com.kursatkumsuz.movie.util.Screen

@Composable
fun SearchList(
    pagingItem: LazyPagingItems<SearchResult>,
    navController: NavController
) {
    LazyColumn {
        items(pagingItem.itemCount) { index ->
            pagingItem[index]?.let {
                MovieListItem(
                    movie = it,
                    navController = navController
                )
            }
        }
        pagingItem.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = pagingItem.loadState.refresh as LoadState.Error
                    item {
                        ErrorDialog(
                            errorMessage = error.error.localizedMessage!!,
                            onRetryClick = { retry() },
                            setShowDialog = { true }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = pagingItem.loadState.append as LoadState.Error
                    item {
                        ErrorDialog(
                            errorMessage = error.error.localizedMessage!!,
                            onRetryClick = { retry() },
                            setShowDialog = { true }
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun MovieListItem(movie: SearchResult, navController: NavController) {
    Column(
        modifier = Modifier
            .size(width = 120.dp, height = 160.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.popBackStack()
                navController.navigate(Screen.DetailScreen.route + "/${movie.id}")
            },
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = "https://image.tmdb.org/t/p/w300/${movie.posterPath}",
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop
        )
    }
}
