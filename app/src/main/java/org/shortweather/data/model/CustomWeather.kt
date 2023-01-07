package org.shortweather.data.model

data class CustomWeatherPrecipitation(
    val time: String,
    val precipitationLevel: String
)

data class CustomWeatherWeather(
    val time: String,
    val temperature: String
)