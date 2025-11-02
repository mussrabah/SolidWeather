package com.muss_coding.solidweather.presentation.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.muss_coding.solidweather.domain.model.WeatherData
import com.muss_coding.solidweather.presentation.weather.WeatherState
import java.time.format.DateTimeFormatter

/*
 * Presentation Layer: Reusable Component
 * This component shows the hourly forecast for today.
 */
@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    // Show only data for the current day
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Today's Forecast",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(weatherData = weatherData)
                }
            }
        }
    }
}

@Composable
private fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm")),
            color = Color.LightGray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(
                        LocalContext.current.resources.getIdentifier(
                            weatherData.weatherType.iconRes,
                            "drawable",
                            LocalContext.current.packageName
                        )
                    )
                    .crossfade(true)
                    .build()
            ),
            contentDescription = weatherData.weatherType.weatherDesc,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}
