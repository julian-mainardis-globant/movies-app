package com.example.data.service

import com.example.data.service.api.MovieApi
import com.example.data.service.util.Constants
import com.example.data.service.util.transformToLocalMovieList
import com.example.domain.entity.Movie
import com.example.domain.service.MovieService
import com.example.domain.util.Result
import com.example.domain.util.TabsEnum

class MovieServiceImpl(private val api: ServiceGenerator) : MovieService {

    override fun getNowPlayingMovies(): Result<List<Movie>> {
        try {
            val callResponse = api.createService(MovieApi::class.java).getNowPlayingMovies()
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.let {
                    return Result.Success(it.transformToLocalMovieList(TabsEnum.NOW_PLAYING))
                }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(Constants.NOT_FOUND))
    }

    override fun getTopRatedMovies(): Result<List<Movie>> {
        try {
            val callResponse = api.createService(MovieApi::class.java).getTopRatedMovies()
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.let {
                    return Result.Success(it.transformToLocalMovieList(TabsEnum.TOP_RATED))
                }
        } catch (e: Exception) {
            return Result.Failure(e)
        }
        return Result.Failure(Exception(Constants.NOT_FOUND))
    }
}
