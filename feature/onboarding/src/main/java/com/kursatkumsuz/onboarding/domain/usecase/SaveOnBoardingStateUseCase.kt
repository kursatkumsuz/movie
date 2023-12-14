package com.kursatkumsuz.onboarding.domain.usecase

import com.kursatkumsuz.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveOnBoardingStateUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) {
        dataStoreRepository.saveOnBoardingState(isCompleted = isCompleted)
    }
}