package com.example.youtube_api.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class InternetConnectivity(private val connectivity: ConnectivityManager): LiveData<Boolean>(){

    constructor(application: Application):this(
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    private val networkCallback = object : ConnectivityManager.NetworkCallback(){

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

    }

    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        connectivity.registerNetworkCallback(builder.build(),networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivity.unregisterNetworkCallback(networkCallback)
    }
}