package com.muss_coding.solidweather.presentation.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Today's Forecast",
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 8.dp)
                    )
                }
            })
        }
    }
}

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(
            text = weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm")),
            color = Color.Gray
        )
        Text(
            text = weatherData.weatherType.iconRes,
            fontSize = 30.sp
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = Color.Black
        )
    }
}
