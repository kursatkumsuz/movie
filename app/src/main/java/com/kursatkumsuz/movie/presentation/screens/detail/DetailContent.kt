package com.kursatkumsuz.movie.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kursatkumsuz.movie.data.model.detail.Genre
import com.kursatkumsuz.movie.domain.model.detail.CastUI
import com.kursatkumsuz.movie.domain.model.movie.detail.MovieDetailUI
import com.kursatkumsuz.movie.presentation.component.PageLoader
import com.kursatkumsuz.movie.presentation.component.detail.WatchButton
import com.kursatkumsuz.movie.util.Constants.IMAGE_BASE_URL


@Composable
fun DetailContent(
    movie: MovieDetailUI?,
    isLoading: Boolean,
    cast: List<CastUI>,
    onBackClick: () -> Unit,
    onSaveWatchlistClick: () -> Unit,
    onWatchClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp / 2
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF000000),
            Color(0xB3000000),
            Color(0x4D000000),
            Color(0x12000000),
            Color(0x4D000000),
            Color(0xB3000000),
            Color(0xFF000000)
        )
    )
    if (isLoading)
        PageLoader()
    else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight.dp),
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = "https://image.tmdb.org/t/p/w780/${movie?.backDropPath}",
                        contentDescription = "Image Photo",
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush = gradient),
                    ) {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back Icon",
                                tint = Color.White
                            )
                        }
                        IconButton(
                            modifier = Modifier.align(Alignment.BottomEnd),
                            onClick = { onSaveWatchlistClick() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Save Icon",
                                tint = Color.Red
                            )
                        }
                    }
                    WatchButton(onWatchClick = onWatchClick)
                }
            }
            item {
                movie?.title?.let {
                    Text(
                        text = it,
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(99.dp))
                        .background(Color(0xFF3F55C6))
                ) {
                    movie?.status?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                movie?.genres?.let { GenreList(genres = it) }
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                movie?.overview?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
            item {
                Text(
                    text = "Cast",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                CastList(cast = cast)
            }
        }
    }

}

@Composable
fun GenreList(genres: List<Genre>) {
    LazyRow() {
        items(genres.size) { index ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .background(Color(0xFF283942))
            ) {
                Text(
                    text = genres[index].name,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
                )
            }
            Divider(modifier = Modifier.width(5.dp))

        }
    }
}


@Composable
fun CastList(cast: List<CastUI>) {
    LazyRow() {
        items(cast.size) { index ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    model = (IMAGE_BASE_URL + "/w300" + cast[index].profilePath),
                    contentDescription = "Cast Profile Image"
                )
                cast[index].name?.let {
                    Text(
                        text = it,
                        color = Color(0xFF6DB9CC),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
                    )
                }
                cast[index].character?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
                    )
                }
            }
            Divider(modifier = Modifier.width(5.dp))

        }
    }
}



