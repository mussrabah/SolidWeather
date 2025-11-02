package com.muss_coding.solidweather.presentation.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muss_coding.solidweather.presentation.weather.WeatherState
import com.muss_coding.solidweather.ui.theme.DeepBlue
import kotlin.math.roundToInt

/*
 * Presentation Layer: NEW Reusable Component
 * This component shows the extra details like pressure, humidity, etc.
 */
@Composable
fun WeatherDetails(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(DeepBlue, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDetailItem(
                    icon = Icons.Default.Opacity,
                    label = "Humidity",
                    value = "${data.humidity.roundToInt()}%"
                )
                WeatherDetailItem(
                    icon = Icons.Default.Air,
                    label = "Wind",
                    value = "${data.windSpeed.roundToInt()} km/h"
                )
                WeatherDetailItem(
                    icon = Icons.Default.ArrowDownward,
                    label = "Pressure",
                    value = "${data.pressure.roundToInt()} hPa"
                )
            }
        }
    }
}

@Composable
private fun WeatherDetailItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.LightGray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = Color.LightGray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
