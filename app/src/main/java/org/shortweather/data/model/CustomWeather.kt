package org.shortweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCustomWeatherDetail(
    val location: String,
    val goOut: ResponseGoOut,
    val goHome: ResponseGoHome,
    val todayWeather: ResponseTodayWeather
) {
    @Serializable
    data class ResponseGoOut(
        val time: String,
        val temp: Int,
        val image: String
    )

    @Serializable
    data class ResponseGoHome(
        val time: String,
        val temp: Int,
        val image: String
    )

    @Serializable
    data class ResponseTodayWeather(
        val humidity: Int,
        val sunrise: String,
        val sunset: String,
        val fineDust: Int,
        val ultraFineDust: Int
    )
}

@Serializable
data class ResponseCustomWeatherTemp(
    val date: String,
    val time: String,
    val temperature: Int,
    val day: Boolean,
    val image: String
)

data class CustomWeatherTemp(
    val data: ResponseCustomWeatherTemp,
    val isCurrent: Boolean = false
)

@Serializable
data class ResponseCustomWeatherRain(
    val date: String,
    val time: String,
    val rain: Int
)

data class CustomWeatherPrecipitation(
    val data: ResponseCustomWeatherRain,
    val isCurrent: Boolean = false
)