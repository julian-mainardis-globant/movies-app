package com.example.domain.database

import com.example.domain.entity.Movie
import com.example.domain.util.Result

interface MovieRepository {
    fun getNowPlayingMovies(): Result<List<Movie>>
    fun updateNowPlayingMovies(movies: List<Movie>)
}
