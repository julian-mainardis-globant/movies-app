package com.example.data.service

import com.example.data.service.api.MovieApi
import com.example.data.service.util.transformToLocalMovieDetail
import com.example.data.service.util.transformToLocalMovieList
import com.example.domain.entity.Movie
import com.example.domain.entity.MovieDetail
import com.example.domain.service.MovieService
import com.example.domain.util.Constants.NOT_FOUND
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum

class MovieServiceImpl(private val api: ServiceGenerator) : MovieService {

    override fun getNowPlayingMovies(): Result<List<Movie>> {
        try {
            val callResponse = api.createService(MovieApi::class.java).getNowPlayingMovies()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it.transformToLocalMovieList(TabsEnum.NOW_PLAYING))
                }
            }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(NOT_FOUND))
    }

    override fun getTopRatedMovies(): Result<List<Movie>> {
        try {
            val callResponse = api.createService(MovieApi::class.java).getTopRatedMovies()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it.transformToLocalMovieList(TabsEnum.TOP_RATED))
                }
            }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(NOT_FOUND))
    }

    override fun getUpcomingMovies(): Result<List<Movie>> {
        try {
            val callResponse = api.createService(MovieApi::class.java).getUpcomingMovies()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it.transformToLocalMovieList(TabsEnum.UPCOMING))
                }
            }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(NOT_FOUND))
    }

    override fun getMovieDetailById(movieId: String): Result<MovieDetail> {
        try {
            val callResponse = api.createService(MovieApi::class.java).getMovieDetailById(movieId)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it.transformToLocalMovieDetail())
                }
            }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(NOT_FOUND))
    }
}
