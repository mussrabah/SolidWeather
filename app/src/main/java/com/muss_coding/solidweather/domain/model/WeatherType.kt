package com.muss_coding.solidweather.domain.model

/*
 * Domain Layer: Model Logic
 * This sealed class helps us map the WMO (World Meteorological Organization)
 * weather codes from the API into human-readable and icon-ready types.
 */
sealed class WeatherType(
    val weatherDesc: String,
    val iconRes: String // In a real app, you'd map this to a drawable resource
) {
    data object ClearSky : WeatherType("Clear sky", "☀️")
    data object MainlyClear : WeatherType("Mainly clear", "🌤️")
    data object PartlyCloudy : WeatherType("Partly cloudy", "🌥️")
    data object Overcast : WeatherType("Overcast", "☁️")
    data object Foggy : WeatherType("Foggy", "🌫️")
    data object Drizzle : WeatherType("Drizzle", "🌦️")
    data object Rain : WeatherType("Rain", "🌧️")
    data object RainShowers : WeatherType("Rain showers", "🌧️")
    data object SnowFall : WeatherType("Snow fall", "❄️")
    data object SnowShowers : WeatherType("Snow showers", "❄️")
    data object Thunderstorm : WeatherType("Thunderstorm", "⛈️")

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45, 48 -> Foggy
                51, 53, 55 -> Drizzle
                61, 63, 65 -> Rain
                66, 67 -> Rain
                80, 81, 82 -> RainShowers
                71, 73, 75 -> SnowFall
                77 -> SnowFall
                85, 86 -> SnowShowers
                95, 96, 99 -> Thunderstorm
                else -> ClearSky
            }
        }
    }
}
