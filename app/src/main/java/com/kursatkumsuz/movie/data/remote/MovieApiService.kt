package com.kursatkumsuz.movie.data.remote

import com.kursatkumsuz.movie.data.model.Movie
import com.kursatkumsuz.movie.data.model.credit.Credit
import com.kursatkumsuz.movie.data.model.detail.MovieDetail
import com.kursatkumsuz.movie.data.model.search.Search
import com.kursatkumsuz.movie.data.model.video.MovieVideo
import com.kursatkumsuz.movie.util.Constants.API_KEY
import com.kursatkumsuz.movie.util.Constants.LANGUAGE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): MovieDetail

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Movie

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Movie

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Movie

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): MovieVideo

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("query") searchString: String = "",
        @Query("include_adult") adult: Boolean = true,
    ): Search

    @GET("movie/{movie_id}/credits")
    suspend fun getCredit(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Credit

}