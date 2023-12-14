package com.kursatkumsuz.splash.domain.usecase

import com.kursatkumsuz.splash.domain.repository.SplashAuthRepository
import kotlinx.coroutines.flow.flow

class IsSignedInUseCase(
    private val splashAuthRepository: SplashAuthRepository
) {
    operator fun invoke() = flow {
        emit(splashAuthRepository.isSignedIn())
    }
}