package com.kursatkumsuz.home.presentation.component

import androidx.compose.foundation.clickable
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
import com.kursatkumsuz.component.ErrorDialog
import com.kursatkumsuz.home.domain.model.ResultHomeUI
import com.kursatkumsuz.home.presentation.PageLoader

@Composable
fun MovieList(
    pagingItem: LazyPagingItems<ResultHomeUI>,
    onNavigateDetailScreen: (String) -> Unit
) {
    LazyRow {
        items(pagingItem.itemCount) { index ->
            MovieListItem(
                movie = pagingItem[index]!!,
                onNavigateDetailScreen = onNavigateDetailScreen
            )
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
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = pagingItem.loadState.append as LoadState.Error
                    item {
                        ErrorDialog(
                            errorMessage = error.error.localizedMessage!!,
                            onRetryClick = { retry() },
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MovieListItem(movie: ResultHomeUI, onNavigateDetailScreen: (String) -> Unit) {
    Column(
        modifier = Modifier
            .size(width = 130.dp, height = 170.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onNavigateDetailScreen(movie.id.toString()) },
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = "https://image.tmdb.org/t/p/w300/${movie.posterPath}",
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop
        )
    }
}