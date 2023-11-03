package com.kursatkumsuz.movie.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.kursatkumsuz.movie.domain.usecase.UseCases
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.kursatkumsuz.movie.data.mappers.toResultHomeUI
import com.kursatkumsuz.movie.data.model.Result
import com.kursatkumsuz.movie.domain.model.movie.ResultHomeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCases
) : ViewModel() {

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
    }

    private fun loadTopRatedMovie() {
        viewModelScope.launch {
            useCase.getTopRatedMovieUseCase.invoke().collect {
                topRatedMovieList.value = it.map { result -> result.toResultHomeUI() }
            }
        }
    }

    private fun loadPopularMovie() {
        viewModelScope.launch {
            useCase.getPopularMovieUseCase.invoke().collect {
                popularMovieList.value = it.map { result -> result.toResultHomeUI() }
            }
        }
    }

    private fun loadNowPlayingMovie() {
        viewModelScope.launch {
            useCase.getNowPlayingMovieUseCase.invoke().collect {
                nowPlayingMovieList.value = it.map { result -> result.toResultHomeUI() }
            }
        }
    }
}