package com.kursatkumsuz.movie.presentation.screens.video

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.movie.domain.usecase.UseCases
import com.kursatkumsuz.movie.util.Constants.MOVIE_ID
import com.kursatkumsuz.movie.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: UseCases
) : ViewModel() {

    var state: MutableState<VideoUIState> = mutableStateOf(VideoUIState())
        private set

    init {
        savedStateHandle.get<String>(MOVIE_ID)?.let {
            loadVideo(movieId = it.toInt())
        }
    }

    private fun loadVideo(movieId: Int) {
        useCases.getMovieVideoUseCase.invoke(movieId = movieId).onEach { response ->
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