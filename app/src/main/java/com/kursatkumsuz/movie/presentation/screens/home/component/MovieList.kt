package com.kursatkumsuz.movie.presentation.screens.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.kursatkumsuz.movie.domain.model.movie.ResultHomeUI
import com.kursatkumsuz.movie.presentation.component.home.ErrorDialog
import com.kursatkumsuz.movie.presentation.screens.home.PageLoader

@Composable
fun MovieList(pagingItem: LazyPagingItems<ResultHomeUI>) {
    LazyRow {
        items(pagingItem.itemCount) { index ->
            MovieListItem(pagingItem[index]!!)
        }
        pagingItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
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
fun MovieListItem(movie: ResultHomeUI) {
    Column(
        modifier = Modifier
            .size(width = 120.dp, height = 160.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = "https://image.tmdb.org/t/p/w300/${movie.posterPath}",
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop
        )
    }
}