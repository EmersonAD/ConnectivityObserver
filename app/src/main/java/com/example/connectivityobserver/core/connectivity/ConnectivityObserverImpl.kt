package com.example.connectivityobserver.core.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import com.example.connectivityobserver.core.connectivity.ConnectivityObserver.Status
import com.example.connectivityobserver.core.connectivity.ConnectivityObserver.Status.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConnectivityObserverImpl(
    context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observer(): Flow<Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    launch {
                        networkCapabilities.run {
                            if (hasCapability(NET_CAPABILITY_INTERNET) && hasCapability(NET_CAPABILITY_VALIDATED)) {
                                send(InternetWithConnection)
                            } else if (hasCapability(NET_CAPABILITY_INTERNET) && !hasCapability(NET_CAPABILITY_VALIDATED)){
                                send(WithoutNetworkAccess)
                            } else if (!hasCapability(NET_CAPABILITY_NOT_SUSPENDED)) {
                                send(NetworkSuspended)
                            }
                        }
                    }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch {
                        send(LostConnection)
                    }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}