package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "movie_detail_table")
data class MovieDetailEntity(
    @PrimaryKey
    val id: String,
    @TypeConverters
    val genres: List<String>,
    val homepage: String,
    val overview: String,
    val imgURL: String,
    val releaseDate: String,
    val tagline: String,
    val title: String,
    val voteAverage: Float,
)
