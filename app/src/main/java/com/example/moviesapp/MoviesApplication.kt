package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MoviesApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)

            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }
}
