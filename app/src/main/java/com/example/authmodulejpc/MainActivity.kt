package com.example.authmodulejpc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.authmodulejpc.presentation.main.AuthModuleApp
import com.example.authmodulejpc.presentation.main.AuthModuleAppBar
import com.example.authmodulejpc.presentation.main.AuthModuleScreen
import com.example.authmodulejpc.ui.theme.AuthModuleJPCTheme
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AuthModuleJPCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AuthModuleApp()
                }

            }
        }
    }
}



