package com.example.di

import com.example.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.domain.usecase.GetNowPlayingMoviesUseCaseImpl
import com.example.domain.usecase.GetTopRatedMoviesUseCase
import com.example.domain.usecase.GetTopRatedMoviesUseCaseImpl
import com.example.domain.usecase.GetUpcomingMoviesUseCase
import com.example.domain.usecase.GetUpcomingMoviesUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetNowPlayingMoviesUseCase> { GetNowPlayingMoviesUseCaseImpl(get(), get()) }
        factory<GetTopRatedMoviesUseCase> { GetTopRatedMoviesUseCaseImpl(get(), get()) }
        factory<GetUpcomingMoviesUseCase> { GetUpcomingMoviesUseCaseImpl(get(), get()) }
    }
}
