package com.example.youtube_api.data.entity.model.remote

import com.example.youtube_api.BuildConfig
import com.example.youtube_api.core.network.BaseDataSource
import com.example.youtube_api.core.network.RetrofitClient


class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()

    }

    suspend fun getPlaylist() = getResult {
        apiService.getPlaylists(
            "snippet,contentDetails",
            "UCJuMbdKSMThk2RpALASyXVQ",
            BuildConfig.API_KEY
        )
    }

    suspend fun getPLaylistItem(playlistId: String) = getResult {
        apiService.getPlaylistItems("", "snippet,contentDetails", playlistId)
    }

    suspend fun getVideo(videoId: String) = getResult {
        apiService.getVideo("","snippet,contentDetails",videoId)
    }
}

