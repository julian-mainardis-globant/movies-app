package com.example.moviesapp

import android.app.Application
import com.example.di.ApiModule.apiModule
import com.example.di.DatabaseModule.dataBaseModule
import com.example.di.RepositoryModule.repositoryModule
import com.example.di.ServiceModule.serviceModule
import com.example.di.UseCaseModule.useCaseModule
import com.example.moviesapp.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class BaseApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    apiModule,
                    dataBaseModule,
                    repositoryModule,
                    useCaseModule,
                    serviceModule,
                    viewModelModule
                )
            )
        }
    }
}
