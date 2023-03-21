package com.kursatkumsuz.movie.domain.usecase.datastore

import com.kursatkumsuz.movie.domain.repository.DataStoreRepository

class SaveOnBoardingStateUseCase(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(isCompleted: Boolean) {
        dataStoreRepository.saveOnBoardingState(isCompleted = isCompleted)
    }

}