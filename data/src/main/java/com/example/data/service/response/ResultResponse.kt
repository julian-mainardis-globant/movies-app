package com.example.data.service.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("results")
    val results: MutableList<MovieResponse>
)
