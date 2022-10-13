package com.example.domain.usecase

import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.service.MovieService
import com.example.domain.util.Result

interface GetNowPlayingMoviesUseCase {
    operator fun invoke(): Result<List<Movie>>
}

class GetNowPlayingMoviesUseCaseImpl(
    private val movieService: MovieService,
    private val database: MovieRepository
) : GetNowPlayingMoviesUseCase {

    override fun invoke(): Result<List<Movie>> {
        return when (val serviceResult = movieService.getNowPlayingMovies()) {
            is Result.Success -> {
                database.updateNowPlayingMovies(serviceResult.data)
                database.getNowPlayingMovies()
            }
            is Result.Failure -> {
                database.getNowPlayingMovies()
            }
        }
    }
}
