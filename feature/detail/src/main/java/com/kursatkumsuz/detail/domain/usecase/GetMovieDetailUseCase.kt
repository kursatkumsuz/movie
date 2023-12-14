package com.kursatkumsuz.detail.domain.usecase

import com.kursatkumsuz.detail.data.mapper.toMovieDetailUI
import com.kursatkumsuz.detail.domain.repository.DetailMovieRepository
import com.kursatkumsuz.util.Response
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetMovieDetailUseCase(
    val detailMovieRepository: DetailMovieRepository
) {
    operator fun invoke(movieId: Int) = flow {
        return@flow try {
            emit(Response.Loading)
            val result = detailMovieRepository.getMovieDetail(movieId = movieId).toMovieDetailUI()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}