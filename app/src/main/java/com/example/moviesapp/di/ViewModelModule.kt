package com.example.moviesapp.di

import com.example.moviesapp.viewmodel.MainActivityViewModel
import com.example.moviesapp.viewmodel.NowPlayingViewModel
import com.example.moviesapp.viewmodel.SplashScreenViewModel
import com.example.moviesapp.viewmodel.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { SplashScreenViewModel() }
        viewModel { MainActivityViewModel() }
        viewModel { NowPlayingViewModel(get()) }
        viewModel { TopRatedViewModel(get()) }
    }
}
