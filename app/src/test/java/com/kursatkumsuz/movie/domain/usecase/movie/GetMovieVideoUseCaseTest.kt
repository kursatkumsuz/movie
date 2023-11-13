package com.kursatkumsuz.movie.domain.usecase.movie

import com.kursatkumsuz.movie.domain.data.repository.FakeMovieRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieVideoUseCaseTest {
    private lateinit var fakeMovieRepository: FakeMovieRepositoryImpl
    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepositoryImpl()
    }

    @Test
    fun `when movie id is found, first result's title should be Video 1` () = runBlocking {
        val movieId = 152
        val result = fakeMovieRepository.getMovieVideo(movieId)
        assert(result.results[1].name == "Video 1")
    }
    @Test
    fun `when video is found, should return not null` () = runBlocking {
        val movieId = 254
        val result = fakeMovieRepository.getMovieVideo(movieId)
        assert(result != null)
    }
}