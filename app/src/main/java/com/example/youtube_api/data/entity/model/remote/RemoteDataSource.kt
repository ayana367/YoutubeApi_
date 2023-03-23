package com.example.youtube_api.data.entity.model.remote

import com.example.youtube_api.BuildConfig
import com.example.youtube_api.core.network.BaseDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory { RemoteDataSource(get()) }
}


class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlaylist() = getResult {
        apiService.getPlaylists(
            "snippet,contentDetails",
            "UCpiEjwzOPvQrmoDk1OlAPKg",
            BuildConfig.API_KEY
        )
    }

    suspend fun getPLaylistItem(playlistId: String) = getResult {
        apiService.getPlaylistItems(BuildConfig.API_KEY, "snippet,contentDetails", playlistId)
    }

    suspend fun getVideo(videoId: String) = getResult {
        apiService.getVideo(BuildConfig.API_KEY,"snippet,contentDetails",videoId)
    }
}

