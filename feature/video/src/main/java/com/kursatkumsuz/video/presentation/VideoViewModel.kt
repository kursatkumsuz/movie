package com.kursatkumsuz.video.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.util.Constants.MOVIE_ID
import com.kursatkumsuz.video.domain.usecase.GetMovieVideoUseCase
import com.kursatkumsuz.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    var state: MutableState<VideoUIState> = mutableStateOf(VideoUIState())
        private set

    init {
        savedStateHandle.get<String>(MOVIE_ID)?.let {
            loadVideo(movieId = it.toInt())
        }
    }

    private fun loadVideo(movieId: Int) {
        getMovieVideoUseCase.invoke(movieId = movieId).onEach { response ->
            when (response) {
                is Response.Success -> {
                    state.value = VideoUIState(movie = response.data)
                }

                is Response.Loading -> {
                    state.value = VideoUIState(isLoading = true)
                }

                is Response.Error -> {
                    state.value = VideoUIState(error = response.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }

}