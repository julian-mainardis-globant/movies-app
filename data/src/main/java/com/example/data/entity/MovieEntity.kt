package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val imgURL: String
)
