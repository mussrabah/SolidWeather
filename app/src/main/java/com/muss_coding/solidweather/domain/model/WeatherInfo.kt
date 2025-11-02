package com.muss_coding.solidweather.domain.model

import java.time.LocalDateTime

/*
 * Domain Layer: Model
 * This is the clean data class that our Presentation layer will use.
 * It is independent of any networking or database code.
 */
data class WeatherInfo(
    // Map of Day -> List of hourly weather data for that day
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
