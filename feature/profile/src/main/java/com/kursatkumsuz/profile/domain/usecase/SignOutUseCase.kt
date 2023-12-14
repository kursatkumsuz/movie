package com.kursatkumsuz.profile.domain.usecase

import com.kursatkumsuz.profile.domain.repository.ProfileAuthenticationRepository
import com.kursatkumsuz.util.Response
import kotlinx.coroutines.flow.flow

class SignOutUseCase(
    private val authenticationRepository: ProfileAuthenticationRepository
) {
    suspend operator fun invoke() = flow{
        try {
            emit(Response.Loading)
            val result = authenticationRepository.signOut()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}