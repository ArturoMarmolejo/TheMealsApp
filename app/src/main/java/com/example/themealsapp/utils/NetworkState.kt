package com.example.themealsapp.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkState @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {


    fun isInternetOn() =
        connectivityManager
            .getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            ?: false
}