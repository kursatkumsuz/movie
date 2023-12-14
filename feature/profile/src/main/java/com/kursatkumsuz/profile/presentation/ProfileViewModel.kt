package com.kursatkumsuz.profile.presentation

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.domain.model.User
import com.kursatkumsuz.domain.usecase.UseCases
import com.kursatkumsuz.profile.domain.usecase.ProfileUseCases
import com.kursatkumsuz.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    private val useCases: UseCases
) : ViewModel() {

    var state = mutableStateOf(ProfileUIState())
        private set
    var imageState = mutableStateOf<String?>(null)
        private set
    var uploadState = mutableStateOf<Boolean>(false)
        private set

    init {
        loadProfileImage()
        loadUserInformation()
    }


    private fun loadUserInformation() {
        useCases.getUserUseCase.invoke().onEach { response ->
            when (response) {
                is Response.Success -> {
                    response.data?.documents?.forEach { document ->
                        val user = document.toObject(User::class.java)
                        state.value = user?.let { ProfileUIState(user = it) }!!
                    }
                }

                is Response.Loading -> {
                    state.value = ProfileUIState(isLoading = true)
                }

                is Response.Error -> {
                    state.value = ProfileUIState(error = response.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveProfileImage(
        imageUrl: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        profileUseCases.saveUserProfileImageUseCase.invoke(imageUrl = imageUrl).onEach { response ->
            when (response) {
                is Response.Success -> {
                    onSuccess()
                    uploadState.value = false
                }
                is Response.Error -> {
                    onError(response.errorMessage)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun loadProfileImage() {
        useCases.getUserProfileImageUseCase.invoke().onEach { response ->
            when (response) {
                is Response.Success -> {
                    response.data?.data?.forEach { (_, image) ->
                        imageState.value = image.toString()
                    }

                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun uploadProfileImage(
        image: Uri,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        profileUseCases.uploadProfileImageUseCase.invoke(image = image).onEach { response ->
            when (response) {
                is Response.Success -> {
                    response.data.storage.downloadUrl.addOnSuccessListener { uri ->
                        saveProfileImage(
                            imageUrl = uri.toString(),
                            onError = onError,
                            onSuccess = onSuccess
                        )
                    }
                }

                is Response.Error -> {
                    onError(response.errorMessage)
                }

                is Response.Loading -> {
                    uploadState.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    fun signOut(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            profileUseCases.signOutUseCase.invoke().onEach { response ->
                when (response) {
                    is Response.Success -> onSuccess()

                    is Response.Error -> onError(response.errorMessage)

                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }
}