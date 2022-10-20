package com.example.data.database

import com.example.data.service.util.Constants
import com.example.data.service.util.transformToLocalMoviesList
import com.example.data.service.util.transformToMovieEntity
import com.example.domain.database.MovieRepository
import com.example.domain.entity.Movie
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieRepository {

    override fun getMoviesByTab(tab: TabsEnum): Result<List<Movie>> =
        movieDao.getMoviesByTab(tab).let {
            if (it.isNotEmpty()) {
                Result.Success(it.transformToLocalMoviesList())
            } else {
                Result.Failure(Exception(Constants.NOT_FOUND))
            }
        }

    override fun updateMovies(movies: List<Movie>) {
        movies.forEach {
            movieDao.insertMovies(it.transformToMovieEntity())
        }
    }
}
