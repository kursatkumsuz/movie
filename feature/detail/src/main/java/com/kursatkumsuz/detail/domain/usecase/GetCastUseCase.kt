package com.kursatkumsuz.detail.domain.usecase

import com.kursatkumsuz.detail.data.mapper.toCastUI
import com.kursatkumsuz.detail.domain.repository.DetailMovieRepository
import com.kursatkumsuz.util.Response
import kotlinx.coroutines.flow.flow

class GetCastUseCase(
    val detailMovieRepository: DetailMovieRepository
) {
    operator fun invoke(movieId: Int) = flow {
        try {
            emit(Response.Loading)
            val result = detailMovieRepository.getCredit(movieId = movieId).cast.map { it.toCastUI() }
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}