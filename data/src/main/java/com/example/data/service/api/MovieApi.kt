package com.example.data.service.api

import com.example.data.service.response.ResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(): Call<ResultResponse>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(): Call<ResultResponse>
}
