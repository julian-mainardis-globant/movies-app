package com.example.domain.usecase

import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.service.MovieService
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum

interface GetUpcomingMoviesUseCase {
    operator fun invoke(): Result<List<Movie>>
}

class GetUpcomingMoviesUseCaseImpl(
    private val movieService: MovieService,
    private val database: MovieRepository
) : GetUpcomingMoviesUseCase {

    override fun invoke(): Result<List<Movie>> =
        when (val serviceResult = movieService.getUpcomingMovies()) {
            is Result.Success -> {
                database.updateMovies(serviceResult.data)
                database.getMoviesByTab(TabsEnum.UPCOMING)
            }
            is Result.Failure -> {
                database.getMoviesByTab(TabsEnum.UPCOMING)
            }
        }
}
