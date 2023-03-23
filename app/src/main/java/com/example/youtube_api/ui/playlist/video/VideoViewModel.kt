package com.example.youtube_api.ui.playlist.video

import androidx.lifecycle.LiveData
import com.example.youtube_api.core.network.result.Resource
import com.example.youtube_api.core.network.ui.BaseViewModel
import com.example.youtube_api.data.entity.model.ItemsItem
import com.example.youtube_api.repository.Repository

class VideoViewModel(private val repository: Repository) : BaseViewModel() {

    fun getVideo(videoId : String) : LiveData<Resource<ItemsItem>>{
        return repository.getVideo(videoId)
    }
}