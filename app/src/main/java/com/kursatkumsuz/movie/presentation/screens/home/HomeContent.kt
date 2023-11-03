package com.kursatkumsuz.movie.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.kursatkumsuz.movie.domain.model.movie.ResultHomeUI
import com.kursatkumsuz.movie.presentation.screens.home.component.MovieList
import com.kursatkumsuz.movie.presentation.screens.home.component.NowPlayingHorizontalPager

@Composable
fun HomeContent(
    topRatedPagingItem: LazyPagingItems<ResultHomeUI>,
    popularPagingItem: LazyPagingItems<ResultHomeUI>,
    nowPlayingPagingItem: LazyPagingItems<ResultHomeUI>
) {
    Column {
        LazyColumn {
            item {
                Text(
                    text = "Now Playing",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
                NowPlayingHorizontalPager(nowPlayingPagingItem)
            }
            item {
                Text(
                    text = "Top Rated",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
                MovieList(pagingItem = topRatedPagingItem)
            }
            item {
                Text(
                    text = "Popular",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
                MovieList(pagingItem = popularPagingItem)
            }
        }
    }
}



@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Loading",
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

