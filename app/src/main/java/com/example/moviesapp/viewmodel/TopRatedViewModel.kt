package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Movie
import com.example.domain.usecase.GetTopRatedMoviesUseCase
import com.example.domain.util.Constants.EMPTY_STRING
import com.example.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopRatedViewModel(private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase) : ViewModel() {

    private var movieState = MutableLiveData<MovieData>()
    fun getMovieState(): LiveData<MovieData> = movieState

    fun getTopRatedMovies() = viewModelScope.launch {
        movieState.postValue(MovieData(state = MovieState.RESPONSE_LOADING))
        withContext(Dispatchers.IO) { getTopRatedMoviesUseCase() }.let { result ->
            when (result) {
                is Result.Success -> {
                    movieState.postValue(
                        MovieData(
                            state = MovieState.RESPONSE_SUCCESS,
                            data = result.data
                        )
                    )
                }
                is Result.Failure -> {
                    movieState.postValue(MovieData(state = MovieState.RESPONSE_ERROR))
                }
            }
        }
    }

    fun onMoviePressed(movieId: String) {
        movieState.value = MovieData(MovieState.MOVIE_PRESSED, movieId = movieId)
    }

    data class MovieData(
        val state: MovieState,
        val data: List<Movie> = emptyList(),
        val movieId: String = EMPTY_STRING
    )

    enum class MovieState {
        RESPONSE_SUCCESS,
        RESPONSE_ERROR,
        RESPONSE_LOADING,
        MOVIE_PRESSED
    }
}
