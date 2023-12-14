package com.kursatkumsuz.home.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kursatkumsuz.home.domain.model.ResultHomeUI
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NowPlayingHorizontalPager(
    nowPlayingPagingItem: LazyPagingItems<ResultHomeUI>,
    onNavigateDetailScreen: (String) -> Unit
) {
    val state = rememberPagerState()
    HorizontalPager(
        state = state,
        count = nowPlayingPagingItem.itemCount
    ) { page ->
        val pageOffset = (state.currentPage - page) + state.currentPageOffset
        val scaleFactor = 0.75f + (1f - 0.75f) * (1f - pageOffset.absoluteValue)
        NowPlayingItem(
            movie = nowPlayingPagingItem[page]!!,
            pageOffset = pageOffset,
            scale = scaleFactor,
            onNavigateDetailScreen = onNavigateDetailScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowPlayingItem(
    movie: ResultHomeUI,
    pageOffset: Float,
    scale: Float,
    onNavigateDetailScreen: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp / 1.2
    val screenHeight = LocalConfiguration.current.screenHeightDp / 1.8

    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onNavigateDetailScreen(movie.id.toString())
        },
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier
            .graphicsLayer {
                lerp(
                    start = 0.10f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
                scaleY = scale
                scaleX = scale

                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
    ) {

        AsyncImage(
            modifier = Modifier
                .width(screenWidth.dp)
                .height(screenHeight.dp)
                .offset {
                    IntOffset(
                        x = (screenWidth.dp * pageOffset).roundToPx(),
                        y = 0,
                    )
                },
            model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
            contentDescription = "Now Playing Image",
            contentScale = ContentScale.Crop
        )
    }
}

