package com.example.youtube_api.di

import com.example.youtube_api.ui.playlist.PlaylistsViewModel
import com.example.youtube_api.ui.playlist.item.ItemViewModel
import com.example.youtube_api.ui.playlist.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
 viewModel { PlaylistsViewModel(get()) }
 viewModel { VideoViewModel(get()) }
 viewModel { ItemViewModel(get()) }
}