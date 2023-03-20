package com.example.youtube_api.ui.playlist.item

import androidx.lifecycle.LiveData
import com.example.youtube_api.core.network.result.Resource
import com.example.youtube_api.core.network.ui.BaseViewModel
import com.example.youtube_api.data.entity.model.Playlists
import com.example.youtube_api.App

class ItemViewModel : BaseViewModel() {
    fun playlists(playlistId: String): LiveData<Resource<Playlists>> {
        return App.repository.getItemList(playlistId)
    }
}