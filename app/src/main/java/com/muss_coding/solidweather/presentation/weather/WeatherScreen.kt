package com.muss_coding.solidweather.presentation.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.muss_coding.solidweather.presentation.weather.components.WeatherCard
import com.muss_coding.solidweather.presentation.weather.components.WeatherForecast

//import androidx.hilt.navigation.compose.hiltViewModel

/*
 * Presentation Layer: Screen (Composable)
 * This is the main UI. It observes the ViewModel's state and
 * displays the appropriate UI (Loading, Error, or Success).
 * It's stateless, just reacting to the state.
 */
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            state.weatherInfo?.let { info ->
                WeatherCard(state = state, backgroundColor = Color(0xFF3F51B5))
                Spacer(modifier = Modifier.height(16.dp))
                WeatherForecast(state = state)
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
