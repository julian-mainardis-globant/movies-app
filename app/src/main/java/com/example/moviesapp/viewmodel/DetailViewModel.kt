package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MovieDetail
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {

    private var movieDetailState = MutableLiveData<MovieDetailData>()
    fun getMovieDetailState(): LiveData<MovieDetailData> = movieDetailState

    fun getMovieDetailById(movieId: String) = viewModelScope.launch {
        movieDetailState.postValue(MovieDetailData(state = MovieDetailState.RESPONSE_LOADING))
        withContext(Dispatchers.IO) { getMovieDetailUseCase(movieId) }.let { result ->
            when (result) {
                is Result.Success -> {
                    movieDetailState.postValue(MovieDetailData(state = MovieDetailState.RESPONSE_SUCCESS, data = result.data))
                }
                is Result.Failure -> {
                    movieDetailState.postValue(MovieDetailData(state = MovieDetailState.RESPONSE_ERROR))
                }
            }
        }
    }

    fun onBackButtonPressed() {
        movieDetailState.value = MovieDetailData(MovieDetailState.BACK_BUTTON_PRESSED)
    }

    data class MovieDetailData(
        val state: MovieDetailState,
        val data: MovieDetail? = null
    )

    enum class MovieDetailState {
        RESPONSE_SUCCESS,
        RESPONSE_ERROR,
        RESPONSE_LOADING,
        BACK_BUTTON_PRESSED
    }
}
