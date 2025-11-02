package com.muss_coding.solidweather.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muss_coding.solidweather.domain.repository.WeatherRepository
import com.muss_coding.solidweather.domain.use_case.GetWeatherUseCase
import com.muss_coding.solidweather.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Presentation Layer: ViewModel
 * This ViewModel follows MVI. It exposes a single StateFlow for the UI
 * to observe and receives Events via the onEvent function.
 * It depends on the GetWeatherUseCase (an abstraction), not the repository.
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val repository: WeatherRepository
) : ViewModel() {

    // The private, mutable state
    private val _state = MutableStateFlow(WeatherState())
    // The public, immutable state for the UI
    val state = _state.asStateFlow()

    init {
        // Load default weather for Warsaw, Poland
        onEvent(WeatherEvent.LoadWeather(52.23, 21.01))
    }

    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.LoadWeather -> {
                loadWeather(event.lat, event.long)
            }
        }
    }

    private fun loadWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            // Set loading state
            _state.update { it.copy(isLoading = true, error = null) }

            // Fetch data
            when (val result = getWeatherUseCase(lat, long)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                else -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
