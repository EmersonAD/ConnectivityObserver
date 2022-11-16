package com.example.connectivityobserver.core.connectivity.oldvalidation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData

class OldVersionImpl : LiveData<Boolean>(), OldVersion {
    override fun getBroadcastReceiver(): BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val connectivityStatus =
                intent?.extras?.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY) ?: true
            postValue(!connectivityStatus)
        }
    }
}