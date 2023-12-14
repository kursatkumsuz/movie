package com.kursatkumsuz.profile.presentation

import com.kursatkumsuz.domain.model.User


data class ProfileUIState(
    var user: User = User(),
    var isImageUploading : Boolean = false,
    var isLoading : Boolean = false,
    var error : String = ""
)
