package com.example.moviesapp.di

import com.example.moviesapp.viewmodel.MainActivityViewModel
import com.example.moviesapp.viewmodel.NowPlayingViewModel
import com.example.moviesapp.viewmodel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { SplashScreenViewModel() }
        viewModel { MainActivityViewModel() }
        viewModel { NowPlayingViewModel(get()) }
    }
}
