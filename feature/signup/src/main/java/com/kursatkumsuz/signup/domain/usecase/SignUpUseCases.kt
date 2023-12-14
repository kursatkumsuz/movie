package com.kursatkumsuz.signup.domain.usecase

import com.kursatkumsuz.signup.domain.usecase.authentication.SignUpWithEmailAndPasswordUseCase
import com.kursatkumsuz.signup.domain.usecase.user.SaveUserUseCase

data class SignUpUseCases(
    val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    val saveUserUseCase: SaveUserUseCase
)
