package com.kursatkumsuz.movie.domain.usecase.datastore

import com.kursatkumsuz.movie.domain.repository.DataStoreRepository

class ReadOnBoardingStateUseCase (
    private val dataStoreRepository: DataStoreRepository
        ) {

     operator fun invoke() = dataStoreRepository.readOnBoardingState()
}