package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getNowPlayingMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlayingMovie(movieEntity: MovieEntity)
}
