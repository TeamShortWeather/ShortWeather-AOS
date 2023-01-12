package org.shortweather.presentation.weeklyweather.viewmodel

import androidx.lifecycle.ViewModel
import org.shortweather.data.model.WeeklyWeather

class WeeklyWeatherViewModel : ViewModel() {
    private val mockWeeklyWeatherList = listOf<WeeklyWeather?>(
        null,
        WeeklyWeather(
            day = "월",
            date = "0112",
            dayImage = "맑음",
            dayRain = 0,
            nightImage= "구름많음",
            nightRain = 70,
            isDay = true,
            minTemp = -2,
            maxTemp = 9
        ),
        WeeklyWeather(
            day = "일",
            date = "0112",
            dayImage = "맑음",
            dayRain = 50,
            nightImage= "구름많음",
            nightRain = 70,
            isDay = true,
            minTemp = -2,
            maxTemp = 9
        )
    )

    fun getMockWeeklyWeatherList(): List<WeeklyWeather?> = mockWeeklyWeatherList
}