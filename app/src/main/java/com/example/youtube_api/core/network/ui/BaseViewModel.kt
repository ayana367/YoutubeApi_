package com.example.youtube_api.core.network.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


open class BaseViewModel: ViewModel() {
    val loading = MutableLiveData<Boolean>()
}