package com.kursatkumsuz.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.kursatkumsuz.domain.model.User
import com.kursatkumsuz.home.domain.model.ResultHomeUI
import com.kursatkumsuz.home.presentation.component.MovieList
import com.kursatkumsuz.home.presentation.component.NowPlayingHorizontalPager

@Composable
fun HomeContent(
    profileImage: String,
    user: User?,
    topRatedPagingItem: LazyPagingItems<ResultHomeUI>,
    popularPagingItem: LazyPagingItems<ResultHomeUI>,
    nowPlayingPagingItem: LazyPagingItems<ResultHomeUI>,
    onNavigateDetailScreen: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 50.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    user?.name?.let {
                        Text(
                            text = "Hi $it",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                    Text(
                        text = "What to Watch",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                ProfileImage(image = profileImage)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = "Now Playing",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            NowPlayingHorizontalPager(
                nowPlayingPagingItem = nowPlayingPagingItem,
                onNavigateDetailScreen = onNavigateDetailScreen
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Top-Rated",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieList(
                pagingItem = topRatedPagingItem,
                onNavigateDetailScreen = onNavigateDetailScreen
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Popular",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieList(
                pagingItem = popularPagingItem,
                onNavigateDetailScreen = onNavigateDetailScreen
            )
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

@Composable
fun ProfileImage(image: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(99.dp))
                .align(Alignment.BottomEnd)
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(99.dp),
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFF00A1),
                            Color(0xFFF0004C)
                        )
                    )
                ),
            contentScale = ContentScale.Crop
        )
    }
}
