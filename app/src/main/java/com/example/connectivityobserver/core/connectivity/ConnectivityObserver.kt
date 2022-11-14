package com.example.connectivityobserver.core.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observer(): Flow<Status>

    enum class Status {
        InternetWithConnection, WithoutNetworkAccess, NetworkSuspended,  LostConnection
    }
}