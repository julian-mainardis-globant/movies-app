package com.example.di

import com.example.data.service.MovieServiceImpl
import com.example.domain.service.MovieService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<MovieService> { MovieServiceImpl(get()) }
    }
}
