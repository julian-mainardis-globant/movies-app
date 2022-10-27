package com.example.domain.entity

data class MovieDetail(
    val homepage: String,
    val genres: List<String>,
    val id: String,
    val overview: String,
    val imgURL: String,
    val releaseDate: String,
    val tagline: String,
    val title: String,
    val voteAverage: Float,
)
