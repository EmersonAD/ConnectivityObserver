package com.example.connectivityobserver.core.connectivity.newvalidation

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData

class NewVersionImpl : LiveData<Boolean>(), NewVersion {
    override fun getNetworkCallback(): ConnectivityManager.NetworkCallback =
        object : ConnectivityManager.NetworkCallback() {

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)

                networkCapabilities.run {
                    if (hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && hasCapability(
                            NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    ) {

                        postValue(true)

                    } else if (hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && !hasCapability(
                            NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    ) {

                        postValue(false)

                    } else if (!hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED)) {

                        postValue(false)

                    }
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(false)
            }
        }
}