package com.example.domain.usecase

import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.service.MovieService
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum

interface GetTopRatedMoviesUseCase {
    operator fun invoke(): Result<List<Movie>>
}

class GetTopRatedMoviesUseCaseImpl(
    private val movieService: MovieService,
    private val database: MovieRepository
) : GetTopRatedMoviesUseCase {

    override fun invoke(): Result<List<Movie>> =
        when (val serviceResult = movieService.getTopRatedMovies()) {
            is Result.Success -> {
                database.updateMovies(serviceResult.data)
                database.getMoviesByTab(TabsEnum.TOP_RATED)
            }
            is Result.Failure -> {
                database.getMoviesByTab(TabsEnum.TOP_RATED)
            }
        }
}
