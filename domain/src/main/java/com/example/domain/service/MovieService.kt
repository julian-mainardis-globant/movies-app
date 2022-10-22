package com.example.domain.service

import com.example.domain.entity.Movie
import com.example.domain.util.Result

interface MovieService {
    fun getNowPlayingMovies(): Result<List<Movie>>
    fun getTopRatedMovies(): Result<List<Movie>>
    fun getUpcomingMovies(): Result<List<Movie>>
}
