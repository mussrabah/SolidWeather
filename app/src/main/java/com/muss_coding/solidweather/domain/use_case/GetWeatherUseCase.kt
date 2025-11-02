package com.muss_coding.solidweather.domain.use_case

import com.muss_coding.solidweather.domain.model.WeatherInfo
import com.muss_coding.solidweather.domain.repository.WeatherRepository
import com.muss_coding.solidweather.domain.util.Resource

/*
 * Domain Layer: Use Case
 * This class encapsulates a single piece of business logic.
 * It calls the repository and handles the logic of what to do.
 * In a more complex app, it might combine multiple repository calls.
 * This makes our ViewModel cleaner (SRP).
 */
class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, long: Double): Resource<WeatherInfo> {
        return repository.getWeatherData(lat, long)
    }
}
