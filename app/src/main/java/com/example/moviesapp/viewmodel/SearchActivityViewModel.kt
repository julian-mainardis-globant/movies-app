package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivityViewModel(private val moviesDB: MovieRepository) : ViewModel() {
    private var searchState = MutableLiveData<SearchData>()
    fun getSearchState(): LiveData<SearchData> = searchState

    fun getMovies() = viewModelScope.launch {
        searchState.postValue(SearchData(state = SearchState.LOADING))
        withContext(Dispatchers.IO) { moviesDB.getMovies() }.let { result ->
            when (result) {
                is Result.Success -> {
                    searchState.postValue(
                        SearchData(
                            state = SearchState.SUCCESS,
                            data = result.data
                        )
                    )
                }
                is Result.Failure -> {
                    searchState.postValue(SearchData(state = SearchState.ERROR))
                }
            }
        }
    }

    fun filter(filter: String, movieList: List<Movie>) {
        val filteredList = movieList.filter { it.title.uppercase().contains(filter.uppercase()) }
        searchState.postValue(SearchData(state = SearchState.FILTERED, data = filteredList))
    }

    data class SearchData(
        val state: SearchState,
        val data: List<Movie> = emptyList()
    )

    enum class SearchState {
        LOADING,
        SUCCESS,
        ERROR,
        FILTERED
    }
}
