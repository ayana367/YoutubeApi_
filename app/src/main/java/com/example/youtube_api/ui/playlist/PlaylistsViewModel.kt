package com.example.youtube_api.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtube_api.core.network.result.Resource
import com.example.youtube_api.core.network.ui.BaseViewModel
import com.example.youtube_api.data.entity.model.Playlists
import com.example.youtube_api.App.Companion.repository

class   PlaylistsViewModel: BaseViewModel() {
        fun playlists() : LiveData<Resource<Playlists>>{
            return repository.getPlaylists()
        }
    }