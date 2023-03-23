package com.example.youtube_api.di

import com.example.youtube_api.repository.Repository
import org.koin.dsl.module

val repoModules = module{
    single { Repository(get()) }

}