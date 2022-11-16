package com.example.connectivityobserver.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.connectivityobserver.R;
import com.example.connectivityobserver.core.connectivity.ConnectivityObserver;
import com.example.connectivityobserver.core.connectivity.ConnectivityWatcher;

public class MainActivity2 extends AppCompatActivity {

    ConnectivityWatcher connectivityObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        connectivityObserver = new ConnectivityWatcher(this);

        connectivityObserver.observe(this, aBoolean ->
                Toast.makeText(this, aBoolean + "", Toast.LENGTH_SHORT).show()
        );
    }
}