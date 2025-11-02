package com.muss_coding.solidweather.presentation.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.muss_coding.solidweather.presentation.weather.WeatherState
import java.time.format.DateTimeFormatter
import androidx.compose.ui.platform.LocalResources

/*
 * Presentation Layer: Reusable Component
 * This is the main "current weather" card.
 *
 * NOTE: This Composable now expects drawable resources in
 * res/drawable/ (e.g., ic_sunny.xml, ic_cloudy.xml)
 * You will need to add these yourself.
 */
@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Warsaw, Poland", // Hardcoded for now
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Today, ${
                        data.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }",
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(24.dp))

                // NEW: Use Coil to load the drawable
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(
                                LocalResources.current.getIdentifier(
                                    data.weatherType.iconRes,
                                    "drawable",
                                    LocalContext.current.packageName
                                )
                            )
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = data.weatherType.weatherDesc,
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}
