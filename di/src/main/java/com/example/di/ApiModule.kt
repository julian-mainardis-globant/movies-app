package com.example.di

import com.example.data.service.ServiceGenerator
import org.koin.dsl.module

object ApiModule {
    val apiModule = module {
        factory { ServiceGenerator() }
    }
}
