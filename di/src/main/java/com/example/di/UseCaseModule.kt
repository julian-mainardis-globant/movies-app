package com.example.di

import com.example.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.domain.usecase.GetNowPlayingMoviesUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetNowPlayingMoviesUseCase> { GetNowPlayingMoviesUseCaseImpl(get(), get()) }
    }
}
