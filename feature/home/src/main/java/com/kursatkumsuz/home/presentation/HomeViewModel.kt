package com.kursatkumsuz.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.kursatkumsuz.domain.model.User
import com.kursatkumsuz.domain.usecase.UseCases
import com.kursatkumsuz.home.data.mapper.toResultHomeUI
import com.kursatkumsuz.home.domain.model.ResultHomeUI
import com.kursatkumsuz.home.domain.usecase.HomeUseCases
import com.kursatkumsuz.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
    private val useCase: UseCases
) : ViewModel() {

    var imageState = mutableStateOf("")
        private set

    var userState = mutableStateOf<User?>(User())
        private set
    var topRatedMovieList: MutableStateFlow<PagingData<ResultHomeUI>> =
        MutableStateFlow(value = PagingData.empty())
        private set

    var popularMovieList: MutableStateFlow<PagingData<ResultHomeUI>> =
        MutableStateFlow(value = PagingData.empty())
        private set

    var nowPlayingMovieList: MutableStateFlow<PagingData<ResultHomeUI>> =
        MutableStateFlow(value = PagingData.empty())
        private set

    init {
        loadTopRatedMovie()
        loadPopularMovie()
        loadNowPlayingMovie()
        loadProfileImage()
        loadUserInformation()
    }

    private fun loadTopRatedMovie() {
        homeUseCases.getTopRatedMovieUseCase.invoke()
            .onEach {
                topRatedMovieList.value = it.map { result -> result.toResultHomeUI() }
            }
            .launchIn(viewModelScope)
    }

    private fun loadUserInformation() {
        useCase.getUserUseCase.invoke()
            .onEach { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.documents?.forEach { document ->
                            val user = document.toObject(User::class.java)
                            userState.value = user

                        }
                    }

                    is Response.Loading -> {
                    }

                    is Response.Error -> {
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun loadPopularMovie() {

        homeUseCases.getPopularMovieUseCase.invoke()
            .onEach {
                popularMovieList.value = it.map { result -> result.toResultHomeUI() }
            }
            .launchIn(viewModelScope)

    }

    private fun loadProfileImage() {
        useCase.getUserProfileImageUseCase.invoke()
            .onEach { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.data?.forEach { (_, image) ->
                            imageState.value = image.toString()
                        }
                    }

                    is Response.Loading -> {}
                    is Response.Error -> {
                        println(response.errorMessage)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun loadNowPlayingMovie() {
        homeUseCases.getNowPlayingMovieUseCase.invoke()
            .onEach {
                nowPlayingMovieList.value = it.map { result -> result.toResultHomeUI() }
            }
            .launchIn(viewModelScope)
    }
}