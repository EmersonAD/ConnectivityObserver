package com.example.connectivityobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.connectivityobserver.core.connectivity.ConnectivityObserver
import com.example.connectivityobserver.core.connectivity.ConnectivityObserverImpl
import com.example.connectivityobserver.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectivityObserver = ConnectivityObserverImpl(applicationContext)
    }
}