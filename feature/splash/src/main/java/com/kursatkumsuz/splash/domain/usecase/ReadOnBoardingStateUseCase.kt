package com.kursatkumsuz.splash.domain.usecase

import com.kursatkumsuz.domain.repository.DataStoreRepository

class ReadOnBoardingStateUseCase(
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke() = dataStoreRepository.readOnBoardingState()
}