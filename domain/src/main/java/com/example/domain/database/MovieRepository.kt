package com.example.domain.database

import com.example.domain.entity.Movie
import com.example.domain.entity.MovieDetail
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum

interface MovieRepository {
    fun getMoviesByTab(tab: TabsEnum): Result<List<Movie>>
    fun updateMovies(movies: List<Movie>)
    fun getMovies(): Result<List<Movie>>
    fun getMovieDetailById(movieId: String): Result<MovieDetail>
    fun updateMovieById(movieDetail: MovieDetail)
}
