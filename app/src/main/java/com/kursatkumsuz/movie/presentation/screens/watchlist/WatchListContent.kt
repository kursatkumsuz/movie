package com.kursatkumsuz.movie.presentation.screens.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie
import com.kursatkumsuz.movie.util.Screen

@Composable
fun WatchListContent(movie: List<WatchListMovie>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LazyColumn(Modifier.padding(10.dp)) {
            items(movie.size) { index ->
                MovieListItem(
                    movie = movie[index],
                    navController = navController
                )
            }
        }

    }
}

@Composable
fun MovieListItem(
    movie: WatchListMovie,
    navController: NavController
) {
    Column {


        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier.size(60.dp),
                model = "https://image.tmdb.org/t/p/w300/" + movie.poster,
                contentDescription = "Watch List Image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(20.dp))
            movie.title?.let { Text(text = it, fontSize = 16.sp, color = Color.White) }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.DetailScreen.route + "/${movie.movieId}")
                }
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Arrow Right Icon",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFFFFFF))
        )
        Spacer(modifier = Modifier.height(10.dp))
    }

}