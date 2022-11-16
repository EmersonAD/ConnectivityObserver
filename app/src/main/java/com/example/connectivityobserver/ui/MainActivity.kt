package com.example.connectivityobserver.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectivityobserver.core.connectivity.ConnectivityWatcher
import com.example.connectivityobserver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var connectivityManager: ConnectivityWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectivityManager = ConnectivityWatcher(this@MainActivity)
    }
}