package com.example.playstorecopy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.playstorecopy.navigation.AppNavGraph
import com.example.playstorecopy.ui.theme.PlayStoreCopyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayStoreCopyTheme {
                AppNavGraph()
            }
        }
    }
}