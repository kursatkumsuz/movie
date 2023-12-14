package com.kursatkumsuz.search.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.kursatkumsuz.component.ErrorDialog
import com.kursatkumsuz.search.domain.model.SearchUI
import com.kursatkumsuz.util.Constants.IMAGE_BASE_URL

@Composable
fun SearchList(
    pagingItem: LazyPagingItems<SearchUI>,
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
fun MovieListItem(movie: SearchUI, onNavigateDetailScreen: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                movie.id?.let { onNavigateDetailScreen(it) }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 100.dp, height = 140.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            model = "${IMAGE_BASE_URL}/w300/${movie.image}",
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            movie.title?.let {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
            Text(
                text = movie.overview + "...",
                fontSize = 12.sp,
                color = Color(0xFF4AAEC0),
                maxLines = 1
            )
            movie.releaseDate?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = Color(0xFF4AAEC0),
                    maxLines = 1
                )
            }
        }
    }
}
