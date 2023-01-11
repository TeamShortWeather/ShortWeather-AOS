package org.shortweather.data.model

import kotlinx.serialization.Serializable

sealed class TodayWeatherTag

data class Newsflash(
    val description: String
) : TodayWeatherTag()

data class FineDust(
    val fineDustLevel: Int
) : TodayWeatherTag()

data class UltrafineDust(
    val ultrafineDustLevel: Int
) : TodayWeatherTag()

@Serializable
data class ResponseTodayWeatherInfo(
    val location: String,
    val compareTemp: Int,
    val compareMessage: String,
    val breakingNews: String?,
    val fineDust: Int,
    val ultrafineDust: Int,
    val day: Boolean,
    val image: String,
    val currentTemp: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val weatherMessage: String
)

@Serializable
data class ResponseTodayWeatherToastInfo(
    val temp: Int,
    val weatherMessage: String
)