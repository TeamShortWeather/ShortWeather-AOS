package org.shortweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeeklyWeather(
    val day: String,
    val date: String,
    val dayImage: String,
    val dayRain: Int,
    val nightImage: String,
    val nightRain: Int,
    val isDay: Boolean,
    val minTemp: Int,
    val maxTemp: Int
)