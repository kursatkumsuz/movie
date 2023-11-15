package com.kursatkumsuz.movie.domain.usecase.user

import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import com.kursatkumsuz.movie.domain.repository.FirebaseStorageRepository
import com.kursatkumsuz.movie.util.Response
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val firebaseStorageRepository: FirebaseStorageRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(name: String, email: String) = flow {
        try {
            emit(Response.Loading)
            val userUid = authenticationRepository.getUserUid()
            val result =
                firebaseStorageRepository.saveUser(userUid = userUid, email = email, name = name)
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "User could not be saved!"))
        }


    }
}