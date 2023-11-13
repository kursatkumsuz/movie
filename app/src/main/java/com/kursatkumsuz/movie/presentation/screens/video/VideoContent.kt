package com.kursatkumsuz.movie.presentation.screens.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.kursatkumsuz.movie.data.model.video.MovieVideo
import com.kursatkumsuz.movie.data.model.video.VideoResult
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun VideoContent(video: MovieVideo, onBackClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        item {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.White
                )
            }
        }
        items(video.results.size) { index ->
            VideoListItem(video = video.results[index])
        }
    }
}

@Composable
fun VideoListItem(
    video: VideoResult,
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        YoutubePlayer(video.key)
        Text(text = video.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.White)
        )
    }
}

@Composable
fun YoutubePlayer(
    videoId: String,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Black)
            .clip(RoundedCornerShape(30.dp)),
        factory = { context ->
            YouTubePlayerView(
                context = context
            ).apply {
                enableAutomaticInitialization = false
                lifecycleOwner.lifecycle.addObserver(this)
                initialize(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                })
            }
        })
}


