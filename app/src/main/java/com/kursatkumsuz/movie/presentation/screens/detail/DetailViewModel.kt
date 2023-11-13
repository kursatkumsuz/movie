package com.kursatkumsuz.movie.presentation.screens.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.movie.domain.model.detail.CastUI
import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie
import com.kursatkumsuz.movie.domain.usecase.UseCases
import com.kursatkumsuz.movie.util.Constants.MOVIE_ID
import com.kursatkumsuz.movie.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state: MutableState<DetailUIState> = mutableStateOf(DetailUIState())
        private set

    var creditList: MutableState<List<CastUI>> = mutableStateOf(listOf())
        private set

    init {
        savedStateHandle.get<String>(MOVIE_ID)?.let {
            loadMovie(movieId = it.toInt())
            loadCast(movieId = it.toInt())
        }
    }

    private fun loadMovie(movieId: Int) {
        useCases.getMovieDetailUseCase.invoke(movieId = movieId).onEach { response ->
            when (response) {
                is Response.Success -> {
                    state.value = DetailUIState(movie = response.data)
                }

                is Response.Loading -> {
                    state.value = DetailUIState(isLoading = true)
                }

                is Response.Error -> {
                    state.value = DetailUIState(error = response.errorMessage)
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun loadCast(movieId: Int) {
        useCases.getCreditUseCase.invoke(movieId = movieId).onEach { response ->
            when (response) {
                is Response.Success -> {
                    val cast = response.data
                    creditList.value = cast
                }

                is Response.Loading -> {}

                is Response.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun saveToWatchlist(movie: WatchListMovie) {
        viewModelScope.launch {
            useCases.saveMovieToWatchlistUseCase(movie = movie)
        }
    }

}