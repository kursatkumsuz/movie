package com.kursatkumsuz.movie.presentation.screens.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose..layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.kursatkumsuz.movie.data.model.search.SearchResult
import com.kursatkumsuz.movie.presentation.component.home.ErrorDialog
import com.kursatkumsuz.movie.presentation.screens.home.PageLoader

@Composable
fun SearchList(
    pagingItem: LazyPagingItems<SearchResult>,
    onNavigateDetailScreen: (String) -> Unit
) {
    LazyColumn {
        items(pagingItem.itemCount) { index ->
            pagingItem[index]?.let {
                MovieListItem(
                    movie = it,
                    onNavigateDetailScreen = onNavigateDetailScreen
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
fun MovieListItem(movie: SearchResult, onNavigateDetailScreen: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onNavigateDetailScreen(movie.id.toString())
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 100.dp, height = 140.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            model = "https://image.tmdb.org/t/p/w300/${movie.posterPath}",
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(
                text = movie.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Text(
                text = movie.overview + "...",
                fontSize = 12.sp,
                color = Color(0xFF4AAEC0),
                maxLines = 3
            )

        }
    }
}
