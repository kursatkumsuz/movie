package com.kursatkumsuz.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.kursatkumsuz.search.data.mapper.toSearchUI
import com.kursatkumsuz.search.domain.model.SearchUI
import com.kursatkumsuz.search.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    var searchedMovieList: MutableStateFlow<PagingData<SearchUI>> =
        MutableStateFlow(value = PagingData.empty())
        private set

    private fun searchMovie(searchString: String) {
        viewModelScope.launch {
            searchMovieUseCase.invoke(searchString).collect { searchResult ->
                searchedMovieList.value = searchResult.map { it.toSearchUI() }
            }
        }
    }
    fun onSearchEvent(searchString: String) {
        searchMovie(searchString = searchString)
    }
}