package com.example.moviesapp.di

import com.example.moviesapp.mvvm.viewmodel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { SplashScreenViewModel() }
    }
}
