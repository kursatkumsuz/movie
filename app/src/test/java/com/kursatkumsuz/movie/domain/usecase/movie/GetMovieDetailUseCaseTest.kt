package com.kursatkumsuz.movie.domain.usecase.movie

import com.kursatkumsuz.movie.domain.data.repository.FakeMovieRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieDetailUseCaseTest {
    private lateinit var fakeMovieRepository: FakeMovieRepositoryImpl
    @Before
    fun setUp() {
         fakeMovieRepository = FakeMovieRepositoryImpl()
    }

    @Test
    fun `when movie is found, should return not null` () = runBlocking {
        val movieId = 238
        val result = fakeMovieRepository.getMovieDetail(movieId)
        assert(result != null)
    }
    @Test
    fun `when movie id is found, title should be Adventure Movie` () = runBlocking {
        val movieId = 238
        val result = fakeMovieRepository.getMovieDetail(movieId)
        assert(result.title == "Adventure Movie")
    }

}