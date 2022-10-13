package com.example.data.database

import com.example.data.service.util.Constants
import com.example.data.service.util.transformToLocalMoviesList
import com.example.data.service.util.transformToMovieEntity
import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.util.Result

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieRepository {

    override fun getNowPlayingMovies(): Result<List<Movie>> =
        movieDao.getNowPlayingMovies().let {
            if (it.isNotEmpty()) {
                Result.Success(it.transformToLocalMoviesList())
            } else {
                Result.Failure(Exception(Constants.NOT_FOUND))
            }
        }

    override fun updateNowPlayingMovies(movies: List<Movie>) {
        movies.forEach {
            movieDao.insertNowPlayingMovie(it.transformToMovieEntity())
        }
    }
}
