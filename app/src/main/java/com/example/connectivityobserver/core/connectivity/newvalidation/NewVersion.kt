package com.example.connectivityobserver.core.connectivity.newvalidation

import android.net.ConnectivityManager.NetworkCallback

interface NewVersion {
    fun getNetworkCallback(): NetworkCallback
}