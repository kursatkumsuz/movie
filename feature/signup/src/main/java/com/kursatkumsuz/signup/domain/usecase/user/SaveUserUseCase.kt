package com.kursatkumsuz.signup.domain.usecase.user

import com.kursatkumsuz.domain.model.User
import com.kursatkumsuz.signup.domain.repository.AuthenticationRepository
import com.kursatkumsuz.signup.domain.repository.FirebaseStorageRepository
import com.kursatkumsuz.util.Response
import kotlinx.coroutines.flow.flow

class SaveUserUseCase(
    val firebaseStorageRepository: FirebaseStorageRepository,
    val authenticationRepository: AuthenticationRepository
) {
        operator fun invoke(user: User) = flow {
        try {
            emit(Response.Loading)
            val userUid = authenticationRepository.getUserUid()
            val result =
                firebaseStorageRepository.saveUser(userUid = userUid, user = user)
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "User could not be saved!"))
        }


    }
}