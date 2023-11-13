package com.kursatkumsuz.movie.domain.data.repository

import androidx.paging.PagingData
import com.kursatkumsuz.movie.data.model.Result
import com.kursatkumsuz.movie.data.model.credit.Credit
import com.kursatkumsuz.movie.data.model.detail.Genre
import com.kursatkumsuz.movie.data.model.detail.MovieDetail
import com.kursatkumsuz.movie.data.model.search.SearchResult
import com.kursatkumsuz.movie.data.model.video.MovieVideo
import com.kursatkumsuz.movie.data.model.video.VideoResult
import com.kursatkumsuz.movie.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class FakeMovieRepositoryImpl : MovieRepository {


    val videoData = listOf(
        MovieVideo(
            id = 152,
            results = listOf(
                VideoResult(
                    id = "5",
                    iso_639_1 = "",
                    iso_3166_1 = "",
                    official = true,
                    name = "Video 1",
                    published_at = "22.05.2018",
                    site = "Youtube",
                    size = 1254,
                    type = "Video",
                    key = "sad45"
                ),
                VideoResult(
                    id = "5",
                    iso_639_1 = "",
                    iso_3166_1 = "",
                    official = true,
                    name = "Video 1",
                    published_at = "22.05.2018",
                    site = "Youtube",
                    size = 1254,
                    type = "Video",
                    key = "sad45"
                ),
            )
        ),
        MovieVideo(
            id = 254,
            results = listOf(
                VideoResult(
                    id = "12",
                    iso_639_1 = "",
                    iso_3166_1 = "",
                    official = false,
                    name = "Video 3",
                    published_at = "12.06.2017",
                    site = "Youtube",
                    size = 1254,
                    type = "Video",
                    key = "dsada55"
                )
            )
        )
    )

    val movieDetailData = listOf(
        MovieDetail(
            adult = false,
            belongsToCollection = "",
            budget = 1000000,
            homepage = "",
            imdbId = "",
            originalTitle = "",
            popularity = 25.0,
            posterPath = "",
            productionCompanies = listOf(),
            productionCountries = listOf(),
            revenue = 5,
            runtime = 15,
            spokenLanguages = listOf(),
            tagline = "",
            video = false,
            voteAverage = 22.5,
            voteCount = 255,
            backDropPath = "Back Drop Path 1",
            genres = listOf(Genre(id = 17, name = "Adventure")),
            status = "UnReleased",
            id = 178,
            originalLanguage = "German",
            overview = " Overview 1",
            releaseDate = "2.09.2020",
            title = "Adventure Movie"
        ),

        MovieDetail(
            adult = false,
            belongsToCollection = "",
            budget = 1000000,
            homepage = "",
            imdbId = "",
            originalTitle = "",
            popularity = 25.0,
            posterPath = "",
            productionCompanies = listOf(),
            productionCountries = listOf(),
            revenue = 5,
            runtime = 15,
            spokenLanguages = listOf(),
            tagline = "",
            video = false,
            voteAverage = 22.5,
            voteCount = 255,
            backDropPath = "Back Drop Path 2",
            genres = listOf(Genre(id = 17, name = "Adventure")),
            status = "UnReleased",
            id = 238,
            originalLanguage = "English",
            overview = " Overview 2",
            releaseDate = "2.09.2020",
            title = "Adventure Movie"
        ),
    )

    override suspend fun getTopRatedMovies(): Flow<PagingData<Result>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovies(): Flow<PagingData<Result>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlayingMovies(): Flow<PagingData<Result>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return movieDetailData.first { data -> data.id == movieId }
    }

    override suspend fun getMovieVideo(movieId: Int): MovieVideo {
        return videoData.first { data -> data.id == movieId }
    }

    override suspend fun getCredit(movieId: Int): Credit {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(searchString: String): Flow<PagingData<SearchResult>> {
        TODO("Not yet implemented")
    }
}