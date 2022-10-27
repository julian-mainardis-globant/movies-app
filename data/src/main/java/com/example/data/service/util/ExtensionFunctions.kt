package com.example.data.service.util

import com.example.data.entity.MovieDetailEntity
import com.example.data.entity.MovieEntity
import com.example.data.service.response.GenresResponse
import com.example.data.service.response.MovieDetailResponse
import com.example.data.service.response.MovieResponse
import com.example.data.service.response.ResultResponse
import com.example.domain.entity.Movie
import com.example.domain.entity.MovieDetail
import com.example.domain.util.Constants
import com.example.domain.util.TabsEnum

fun ResultResponse.transformToLocalMovieList(tabsEnum: TabsEnum): List<Movie> {
    val moviesList = mutableListOf<Movie>()
    results.forEach {
        moviesList.add(
            Movie(
                id = it.id,
                imgURL = it.getImgURL(),
                title = it.title,
                voteAverage = it.voteAverage,
                tab = tabsEnum
            )
        )
    }
    return moviesList
}

fun Movie.transformToMovieEntity() =
    MovieEntity(
        id = this.id,
        title = this.title,
        imgURL = this.imgURL,
        voteAverage = this.voteAverage,
        tab = this.tab
    )

fun MovieEntity.transformToLocalMovie() =
    Movie(
        id = this.id,
        title = this.title,
        imgURL = this.imgURL,
        voteAverage = this.voteAverage,
        tab = this.tab
    )

fun List<MovieEntity>.transformToLocalMoviesList(): List<Movie> =
    this.map { it.transformToLocalMovie() }

fun MovieDetailResponse.transformToLocalMovieDetail() =
    MovieDetail(
        homepage = this.homepage,
        genres = this.genres.getListOfNames(),
        id = this.id,
        overview = this.overview,
        imgURL = this.imgPath,
        releaseDate = this.releaseDate,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage
    )

fun MutableList<GenresResponse>.getListOfNames(): List<String> {
    val movieGenres = mutableListOf<String>()
    forEach {
        movieGenres.add(it.genreName)
    }
    return movieGenres
}

fun MovieDetail.transformToMovieDetailEntity() =
    MovieDetailEntity(
        homepage = this.homepage,
        genres = this.genres,
        id = this.id,
        overview = this.overview,
        imgURL = this.imgURL,
        releaseDate = this.releaseDate,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage
    )

fun MovieDetailEntity.transformToLocalMovieDetail() =
    MovieDetail(
        homepage = this.homepage,
        genres = this.genres,
        id = this.id,
        overview = this.overview,
        imgURL = this.imgURL,
        releaseDate = this.releaseDate,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage
    )

fun MovieResponse.getImgURL() = "${Constants.BASE_URL_IMG}${this.imgPath}"
