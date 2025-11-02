package com.muss_coding.solidweather.presentation.weather

/*
 * Presentation Layer: MVI Event (or Action/Intent)
 * These are the actions that the UI can send to the ViewModel
 * to trigger some business logic.
 */
sealed interface WeatherEvent {
    data class LoadWeather(val lat: Double, val long: Double) : WeatherEvent
    // You could add other events like RefreshWeather, SearchCity, etc.
}
