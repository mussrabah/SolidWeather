package com.muss_coding.solidweather.domain.model

/*
 * Domain Layer: Model Logic
 * This sealed class helps us map the WMO (World Meteorological Organization)
 * weather codes from the API into human-readable and icon-ready types.
 */
/*
 * Domain Layer: Model Logic
 * This sealed class helps us map the WMO (World Meteorological Organization)
 * weather codes from the API into human-readable and icon-ready types.
 *
 * UPDATE: We are now using String names for drawable resources
 * instead of emojis. You will need to add these drawables
 * (e.g., ic_sunny.xml) to your res/drawable folder.
 */
sealed class WeatherType(
    val weatherDesc: String,
    val iconRes: String // This is now a String NAME for a drawable
) {
    data object ClearSky : WeatherType("Clear sky", "ic_sunny")
    data object MainlyClear : WeatherType("Mainly clear", "ic_cloudy")
    data object PartlyCloudy : WeatherType("Partly cloudy", "ic_cloudy")
    data object Overcast : WeatherType("Overcast", "ic_cloudy")
    data object Foggy : WeatherType("Foggy", "ic_very_cloudy")
    data object Drizzle : WeatherType("Drizzle", "ic_rainshower")
    data object Rain : WeatherType("Rain", "ic_rainy")
    data object RainShowers : WeatherType("Rain showers", "ic_rainshower")
    data object SnowFall : WeatherType("Snow fall", "ic_snowy")
    data object SnowShowers : WeatherType("Snow showers", "ic_snowy")
    data object Thunderstorm : WeatherType("Thunderstorm", "ic_thunder")

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
