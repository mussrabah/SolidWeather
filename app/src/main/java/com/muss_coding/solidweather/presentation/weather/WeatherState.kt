package com.muss_coding.solidweather.presentation.weather

import com.muss_coding.solidweather.domain.model.WeatherInfo


/*
 * Presentation Layer: MVI State
 * This data class represents the entire state of our WeatherScreen.
 * The UI will observe this state and recompose whenever it changes.
 */
data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
