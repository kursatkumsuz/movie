package com.kursatkumsuz.video.domain.usecase

import com.kursatkumsuz.video.domain.repository.VideoRepository
import com.kursatkumsuz.util.Response
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) {
    operator fun invoke(movieId: Int) = flow {
        try {
            emit(Response.Loading)
            val result = videoRepository.getMovieVideo(movieId = movieId)
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}