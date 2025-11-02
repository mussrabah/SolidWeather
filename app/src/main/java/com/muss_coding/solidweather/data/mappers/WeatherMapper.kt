package com.muss_coding.solidweather.data.mappers

import com.muss_coding.solidweather.data.remote.dto.WeatherDto
import com.muss_coding.solidweather.domain.model.WeatherData
import com.muss_coding.solidweather.domain.model.WeatherInfo
import com.muss_coding.solidweather.domain.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
 * Data Layer: Mapper
 * This file is responsible for "mapping" (converting) the DTO (Data Layer)
 * into the clean WeatherInfo object (Domain Layer) that the rest of
 * our app will use. This is a key part of Clean Architecture.
 */

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = hourly.time.mapIndexed { index, time ->
        val temperature = hourly.temperatures[index]
        val weatherCode = hourly.weatherCodes[index]
        val humidity = hourly.humidities[index]
        val windSpeed = hourly.windSpeeds[index]
        val pressure = hourly.pressures[index]

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                humidity = humidity,
                windSpeed = windSpeed,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24 // Group by day
    }.mapValues { entry ->
        entry.value.map { it.data } // Get just the WeatherData
    }

    // Get current time to find current weather
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}
