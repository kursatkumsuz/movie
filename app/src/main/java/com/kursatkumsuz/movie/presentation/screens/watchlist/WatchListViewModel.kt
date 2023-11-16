package com.kursatkumsuz.movie.presentation.screens.watchlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.movie.domain.model.watchlist.WatchListMovie
import com.kursatkumsuz.movie.domain.usecase.UseCases
import com.kursatkumsuz.movie.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var state: MutableState<WatchListUIState> = mutableStateOf(WatchListUIState())
        private set

    init {
        loadWatchlist()
    }

    private fun loadWatchlist() {
        useCases.getWatchlistUseCase.invoke().onEach { response ->
            when (response) {
                is Response.Success -> {
                    response.data?.toObjects(WatchListMovie::class.java)?.let { list ->
                        state.value = WatchListUIState(movie = list)
                    }
                }

                is Response.Loading -> {
                    state.value = WatchListUIState(loading = true)
                }

                is Response.Error -> {
                    state.value = WatchListUIState(error = response.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun deleteWatchList(movieId: String) {
        viewModelScope.launch {
            useCases.deleteWatchListUseCase.invoke(movieId)
            loadWatchlist()
        }
    }

}