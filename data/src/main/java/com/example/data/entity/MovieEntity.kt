package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.util.TabsEnum

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey
    val id: String,
    val imgURL: String,
    val title: String,
    val voteAverage: Float,
    val tab: TabsEnum
)
