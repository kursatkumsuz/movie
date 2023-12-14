package com.kursatkumsuz.video.data.repository

import com.kursatkumsuz.video.model.MovieVideo
import com.kursatkumsuz.video.data.remote.VideoApiService
import com.kursatkumsuz.video.domain.repository.VideoRepository

class VideoRepositoryImpl (
    private val videoApiService: VideoApiService
) : VideoRepository {
    override suspend fun getMovieVideo(movieId: Int): MovieVideo {
        return videoApiService.getMovieVideos(movieId = movieId)
    }
}