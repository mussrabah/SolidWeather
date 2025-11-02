package com.muss_coding.solidweather.presentation.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.muss_coding.solidweather.presentation.weather.components.WeatherCard
import com.muss_coding.solidweather.presentation.weather.components.WeatherDetails
import com.muss_coding.solidweather.presentation.weather.components.WeatherForecast
import com.muss_coding.solidweather.ui.theme.DarkBlue
import com.muss_coding.solidweather.ui.theme.DeepBlue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Pull to refresh state
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.onEvent(WeatherEvent.Refresh) }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            // NEW: Gradient Background
            .background(
                Brush.verticalGradient(
                    colors = listOf(DarkBlue, DeepBlue)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState) // NEW: Add pull refresh
                .verticalScroll(rememberScrollState()) // NEW: Make screen scrollable
                .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            state.weatherInfo?.let {
                // NEW: Redesigned Weather Card
                WeatherCard(
                    state = state,
                    // Transparent background to show gradient
                    backgroundColor = Color.Transparent
                )
                Spacer(modifier = Modifier.height(24.dp))
                // NEW: Redesigned Forecast
                WeatherForecast(state = state)
                Spacer(modifier = Modifier.height(24.dp))
                // NEW: Weather Details Card
                WeatherDetails(state = state)
            }
        }

        // NEW: PullRefreshIndicator is separate from the main loading
        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = DeepBlue,
            contentColor = Color.White
        )

        // Show full-screen loading indicator ONLY on initial load
        if (state.isLoading && state.weatherInfo == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        // Show error message
        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}
