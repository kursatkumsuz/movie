package com.kursatkumsuz.video.data.remote

import com.kursatkumsuz.util.Constants
import com.kursatkumsuz.video.model.MovieVideo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApiService {
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
    ): MovieVideo
}