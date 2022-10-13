package com.example.data.service.util

import com.example.data.entity.MovieEntity
import com.example.data.service.response.MovieResponse
import com.example.data.service.response.ResultResponse
import com.example.domain.entity.Movie

fun ResultResponse.transformToLocalMovieList(): List<Movie> {
    val moviesList = mutableListOf<Movie>()
    results.forEach {
        moviesList.add(
            Movie(
                it.id,
                it.title,
                it.getImgURL()
            )
        )
    }
    return moviesList
}

fun MovieEntity.transformToLocalMovie() =
    Movie(
        this.id,
        this.title,
        this.imgURL
    )

fun Movie.transformToMovieEntity() =
    MovieEntity(
        this.id,
        this.title,
        this.imgURL
    )

fun List<MovieEntity>.transformToLocalMoviesList(): List<Movie> =
    this.map { it.transformToLocalMovie() }

fun MovieResponse.getImgURL() = "${Constants.BASE_URL_IMG}${this.imgPath}"
