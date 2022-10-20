package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.MovieEntity
import com.example.domain.util.TabsEnum

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table WHERE tab = :tabsEnum")
    fun getMoviesByTab(tabsEnum: TabsEnum): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: MovieEntity)
}
