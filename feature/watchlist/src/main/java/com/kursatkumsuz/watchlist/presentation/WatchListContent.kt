package com.kursatkumsuz.watchlist.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kursatkumsuz.domain.model.WatchListMovie
import com.kursatkumsuz.util.Constants.IMAGE_BASE_URL
import androidx.compose.material3.*


@Composable
fun WatchListContent(
    movie: List<WatchListMovie>,
    onDelete: (String) -> Unit,
    onNavigateDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        WatchList(
            movie = movie,
            onNavigateDetail = onNavigateDetail,
            onDelete = onDelete,
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchList(
    movie: List<WatchListMovie>,
    onDelete: (String) -> Unit,
    onNavigateDetail: (String) -> Unit
) {

    LazyColumn(contentPadding = PaddingValues(top = 20.dp, bottom = 60.dp)) {
        item {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Your Watchlist",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
        items(count = movie.size, key = { index ->
            movie[index].movieId.toString()
        }) { index ->

            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                val movieId = movie[index].movieId.toString()
                onDelete(movieId)
                LaunchedEffect(key1 = Unit) {
                    dismissState.reset()
                }
            }

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(
                    DismissDirection.EndToStart
                ),
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> MaterialTheme.colorScheme.primaryContainer
                            else -> Color.Red
                        }, label = ""
                    )
                    val icon = Icons.Default.Delete

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f,
                        label = ""
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .background(color)
                            .clip(RoundedCornerShape(12.dp)),
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier
                                .scale(scale)
                                .align(Alignment.CenterEnd),
                        )
                    }
                },
                dismissContent = {
                    MovieListItem(
                        movie = movie[index],
                        onNavigateDetail = { id -> onNavigateDetail(id) })
                }
            )
        }
    }
}

@Composable
fun MovieListItem(
    movie: WatchListMovie,
    onNavigateDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(CircleShape)
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = IMAGE_BASE_URL + "/w300" + movie.poster,
                contentDescription = "Watch List Image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(20.dp))
            movie.title?.let { Text(text = it, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground) }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    onNavigateDetail(movie.movieId.toString())
                }
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Arrow Right Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))

    }
}