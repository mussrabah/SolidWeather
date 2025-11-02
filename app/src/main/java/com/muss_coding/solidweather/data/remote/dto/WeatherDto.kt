package com.muss_coding.solidweather.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*
 * Data Layer: Data Transfer Object (DTO)
 * This class maps directly to the JSON response from the API.
 * It's kept separate from our Domain model.
 *
 * FIX: Added @JsonClass(generateAdapter = true) to tell Moshi
 * to create a JSON adapter for this class.
 */
@JsonClass(generateAdapter = true)
data class WeatherDto(
    @field:Json(name = "hourly")
    val hourly: HourlyWeatherDto
)

@JsonClass(generateAdapter = true)
data class HourlyWeatherDto(
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name ="pressure_msl")
    val pressures: List<Double>
)
