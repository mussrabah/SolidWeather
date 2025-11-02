package com.muss_coding.solidweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.muss_coding.solidweather.presentation.weather.WeatherScreen
import com.muss_coding.solidweather.ui.theme.SolidWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolidWeatherTheme {
                // NEW: Make status bar transparent
                SetTransparentSystemBars()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherScreen()
                }
            }
        }
    }
}

@Composable
fun SetTransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
}
