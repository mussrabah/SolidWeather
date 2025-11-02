package com.muss_coding.solidweather.data.remote

import com.muss_coding.solidweather.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Data Layer: Retrofit API Interface
 * We use Open-Meteo. No API key needed.
 */
interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto
}
