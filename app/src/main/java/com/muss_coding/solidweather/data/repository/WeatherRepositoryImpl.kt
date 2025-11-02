package com.muss_coding.solidweather.data.repository

import com.muss_coding.solidweather.data.mappers.toWeatherInfo
import com.muss_coding.solidweather.data.remote.WeatherApi
import com.muss_coding.solidweather.domain.model.WeatherInfo
import com.muss_coding.solidweather.domain.repository.WeatherRepository
import com.muss_coding.solidweather.domain.util.Resource
import javax.inject.Inject

/*
 * Data Layer: Repository Implementation
 * This is the concrete implementation of our Domain layer's repository interface.
 * It takes the API as a dependency and calls it, mapping the DTO to our domain model.
 * It also handles error catching and wrapping the result in our 'Resource' class.
 */
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val dto = api.getWeatherData(
                lat = lat,
                long = long
            )
            Resource.Success(
                data = dto.toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}
