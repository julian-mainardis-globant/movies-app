package com.example.data.service.response

import com.google.gson.annotations.SerializedName

class MovieDetailResponse(
    @SerializedName("genres")
    val genres: MutableList<GenresResponse>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val imgPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Float
)
