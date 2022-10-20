package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.util.TabsEnum

@Entity(tableName = "movie_table")
class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val imgURL: String,
    val voteAverage: Float,
    val tab: TabsEnum
)
