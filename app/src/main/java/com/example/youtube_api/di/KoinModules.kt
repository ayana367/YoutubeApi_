package com.example.youtube_api.di

import com.example.youtube_api.core.network.networkModule
import com.example.youtube_api.data.entity.model.remote.remoteDataSourceModule

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModule,
    remoteDataSourceModule
)