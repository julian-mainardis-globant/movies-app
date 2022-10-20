package com.example.data.service.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("poster_path")
    val imgPath: String
)
