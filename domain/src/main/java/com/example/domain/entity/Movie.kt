package com.example.domain.entity

import com.example.domain.util.TabsEnum

data class Movie(
    val id: String,
    val title: String,
    val imgURL: String,
    val voteAverage: Float,
    val tab: TabsEnum
)
