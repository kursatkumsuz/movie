package com.kursatkumsuz.movie.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.kursatkumsuz.movie.data.model.search.SearchResult
import com.kursatkumsuz.movie.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var searchedMovieList: MutableStateFlow<PagingData<SearchResult>> =
        MutableStateFlow(value = PagingData.empty())
        private set

    private fun searchMovie(searchString: String) {
        viewModelScope.launch {
            useCases.searchMovieUseCase.invoke(searchString).collect {
                searchedMovieList.value = it
            }
        }
    }

    fun onSearchEvent(searchString: String) {
        searchMovie(searchString = searchString)
    }
}