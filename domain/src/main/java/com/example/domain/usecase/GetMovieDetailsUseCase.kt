package com.example.domain.usecase

import com.example.domain.database.MovieRepository
import com.example.domain.entity.MovieDetail
import com.example.domain.service.MovieService
import com.example.domain.util.Result

interface GetMovieDetailUseCase {
    operator fun invoke(movieId: String): Result<MovieDetail>
}

class GetMovieDetailsUseCaseImpl(
    private val movieDetailService: MovieService,
    private val database: MovieRepository
) : GetMovieDetailUseCase {

    override fun invoke(movieId: String): Result<MovieDetail> =
        when (val serviceResult = movieDetailService.getMovieDetailById(movieId)) {
            is Result.Success -> {
                database.updateMovieById(serviceResult.data)
                database.getMovieDetailById(serviceResult.data.id)
            }
            is Result.Failure -> {
                database.getMovieDetailById(movieId)
            }
        }
}
