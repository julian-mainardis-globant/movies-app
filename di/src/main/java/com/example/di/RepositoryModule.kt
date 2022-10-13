package com.example.di

import com.example.data.database.MovieRepositoryImpl
import com.example.domain.database.MovieRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory<MovieRepository> { MovieRepositoryImpl(get()) }
    }
}
