package org.shortweather.data.model

sealed class TodayWeatherTag

data class Newsflash(
    val description: String
) : TodayWeatherTag()

data class FineDust(
    val fineDustLevel: String
) : TodayWeatherTag()

data class UltrafineDust(
    val ultrafineDustLevel: String
) : TodayWeatherTag()