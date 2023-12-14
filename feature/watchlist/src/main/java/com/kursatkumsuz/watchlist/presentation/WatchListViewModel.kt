package com.kursatkumsuz.watchlist.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatkumsuz.domain.model.WatchListMovie
import com.kursatkumsuz.watchlist.domain.usecase.WatchListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val watchListUseCases: WatchListUseCases
) : ViewModel() {

    var state: MutableState<WatchListUIState> = mutableStateOf(WatchListUIState())
        private set

    init {
        loadWatchlist()
    }

    private fun loadWatchlist() {
        watchListUseCases.getWatchListUseCase.invoke().onEach { response ->
            when (response) {
                is com.kursatkumsuz.util.Response.Success -> {
                    response.data?.toObjects(WatchListMovie::class.java)?.let { list ->
                        state.value = WatchListUIState(movie = list)
                    }
                }

                is com.kursatkumsuz.util.Response.Loading -> {
                    state.value = WatchListUIState(loading = true)
                }

                is com.kursatkumsuz.util.Response.Error -> {
                    state.value = WatchListUIState(error = response.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteWatchList(movieId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            watchListUseCases.deleteWatchListUseCase.invoke(movieId).onEach { response ->
                when (response) {
                    is com.kursatkumsuz.util.Response.Success -> {
                        onSuccess()
                        delay(200)
                        loadWatchlist ()
                    }

                    is com.kursatkumsuz.util.Response.Error -> onError(response.errorMessage)
                    else -> {}
                }
            }.launchIn(viewModelScope)

        }
    }

}