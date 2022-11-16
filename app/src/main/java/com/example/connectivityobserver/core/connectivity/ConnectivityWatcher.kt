package com.example.connectivityobserver.core.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.os.Build
import androidx.lifecycle.LiveData
import com.example.connectivityobserver.core.connectivity.newvalidation.NewVersion
import com.example.connectivityobserver.core.connectivity.oldvalidation.OldVersion

class ConnectivityWatcher(
    private val context: Context,
    private val newVersion: NewVersion,
    private val oldVersion: OldVersion
) : LiveData<Boolean>() {

    private lateinit var networkCallback: NetworkCallback
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onActive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            networkCallback = newVersion.getNetworkCallback()
            manager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val intentFilter = IntentFilter(CONNECTIVITY_ACTION)
            broadcastReceiver = oldVersion.getBroadcastReceiver()
            context.registerReceiver(broadcastReceiver, intentFilter)
        }
    }

    override fun onInactive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            manager.unregisterNetworkCallback(networkCallback)
        } else {
            context.unregisterReceiver(broadcastReceiver)
        }
    }
}


