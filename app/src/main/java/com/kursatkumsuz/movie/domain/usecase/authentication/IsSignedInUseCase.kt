package com.kursatkumsuz.movie.domain.usecase.authentication

import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.flow

class IsSignedInUseCase(
    private val authRepository: AuthenticationRepository
) {
    operator fun invoke() = flow {
        emit(authRepository.isSignedIn())
    }
}