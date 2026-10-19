package com.example.guestapp

import GuestDatabaseHelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.guestapp.core.Routes
import com.example.guestapp.ui.screens.GuestListScreen

class MainActivity : ComponentActivity() {

    private val dbHelper by lazy { GuestDatabaseHelper(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ){
                    Routes()
                }
            }
        }
    }
    //fecha o BD quando encerra o app
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}