package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Movie
import com.example.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NowPlayingViewModel(private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase) : ViewModel() {

    private var movieState = MutableLiveData<MovieData>()
    fun getMovieState(): LiveData<MovieData> = movieState

    fun getNowPlayingMovies() = viewModelScope.launch {
        movieState.postValue(MovieData(state = MovieState.RESPONSE_LOADING))
        withContext(Dispatchers.IO) { getNowPlayingMoviesUseCase() }.let { result ->
            when (result) {
                is Result.Success -> {
                    movieState.postValue(MovieData(state = MovieState.RESPONSE_SUCCESS, data = result.data))
                }
                is Result.Failure -> {
                    movieState.postValue(MovieData(state = MovieState.RESPONSE_ERROR))
                }
            }
        }
    }

    data class MovieData(
        val state: MovieState,
        val data: List<Movie> = emptyList()
    )

    enum class MovieState {
        RESPONSE_SUCCESS,
        RESPONSE_ERROR,
        RESPONSE_LOADING
    }
}
