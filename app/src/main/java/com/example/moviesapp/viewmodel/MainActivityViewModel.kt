package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.utils.Constants.EMPTY_DATABASE

class MainActivityViewModel() : ViewModel() {

    private lateinit var result: String
    private var moviesState = MutableLiveData<MoviesData>()

    fun getMovieState(): LiveData<MoviesData> = moviesState

    fun getMovies(connect: Boolean) {
        result = EMPTY_DATABASE
        moviesState.value = MoviesData(MoviesState.LOADING)
        if (!connect) {
            moviesState.value = MoviesData(MoviesState.EMPTY_DATABASE)
        }
    }

    data class MoviesData(
        val mState: MoviesState
    )

    enum class MoviesState {
        LOADING,
        EMPTY_DATABASE
    }
}
