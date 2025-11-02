package com.muss_coding.solidweather.domain.repository

import com.muss_coding.solidweather.domain.model.WeatherInfo
import com.muss_coding.solidweather.domain.util.Resource


/*
 * Domain Layer: Repository Interface (Abstraction)
 * This is the abstraction that the Domain and Presentation layers will
 * depend on. This follows the Dependency Inversion Principle (DIP).
 * The Data layer will provide the concrete implementation.
 */
interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
