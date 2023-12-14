package com.kursatkumsuz.profile.domain.usecase

import com.kursatkumsuz.profile.domain.repository.ImageRepository
import com.kursatkumsuz.util.Response
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class SaveUserProfileImageUseCase(
    private val imageRepository: ImageRepository
) {
    operator fun invoke(imageUrl: String) = flow {
        try {
            emit(Response.Loading)
            val result = imageRepository.saveProfileImage(imageUrl = imageUrl).await()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Photo could not be saved!"))
        }
    }
}