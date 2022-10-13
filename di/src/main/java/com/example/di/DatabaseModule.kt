package com.example.di

import androidx.room.Room
import com.example.data.database.MovieDatabase
import org.koin.dsl.module

object DatabaseModule {
    private const val DATA_BASE_NAME = "MoviesRepository"

    val dataBaseModule = module {
        single { Room.databaseBuilder(get(), MovieDatabase::class.java, DATA_BASE_NAME).build() }
        single { get<MovieDatabase>().movieDao() }
    }
}
