package org.shortweather.presentation.weeklyweather.viewmodel

import androidx.lifecycle.ViewModel
import org.shortweather.data.model.WeeklyWeather

class WeeklyWeatherViewModel : ViewModel() {
    private val mockWeeklyWeatherList = listOf(
        WeeklyWeather(
            day = "오늘",
            date = "0114",
            dayImage = "비 또는 눈",
            dayRain = 60,
            nightImage = "진눈깨비",
            nightRain = 60,
            isDay = true,
            minTemp = 6,
            maxTemp = 8
        ),
        WeeklyWeather(
            day = "내일",
            date = "0115",
            dayImage = "진눈깨비",
            dayRain = 60,
            nightImage = "흐림",
            nightRain = 30,
            isDay = true,
            minTemp = 0,
            maxTemp = 3
        ),
        WeeklyWeather(
            day = "월",
            date = "0116",
            dayImage = "구름많음",
            dayRain = 30,
            nightImage = "맑음",
            nightRain = 0,
            isDay = true,
            minTemp = -5,
            maxTemp = 2
        ),
        WeeklyWeather(
            day = "화",
            date = "0117",
            dayImage = "구름많음",
            dayRain = 30,
            nightImage = "구름많음",
            nightRain = 30,
            isDay = true,
            minTemp = -7,
            maxTemp = 1
        ),
        WeeklyWeather(
            day = "수",
            date = "0118",
            dayImage = "맑음",
            dayRain = 0,
            nightImage = "맑음",
            nightRain = 0,
            isDay = true,
            minTemp = -4,
            maxTemp = 2
        ),
        WeeklyWeather(
            day = "목",
            date = "0119",
            dayImage = "구름많음",
            dayRain = 30,
            nightImage = "구름많음",
            nightRain = 30,
            isDay = true,
            minTemp = -5,
            maxTemp = 3
        ),
        WeeklyWeather(
            day = "금",
            date = "0120",
            dayImage = "맑음",
            dayRain = 0,
            nightImage = "맑음",
            nightRain = 0,
            isDay = true,
            minTemp = -6,
            maxTemp = -1
        ),
        WeeklyWeather(
            day = "토",
            date = "0121",
            dayImage = "맑음",
            dayRain = 0,
            nightImage = "맑음",
            nightRain = 0,
            isDay = true,
            minTemp = -8,
            maxTemp = -1
        ),
        WeeklyWeather(
            day = "일",
            date = "0122",
            dayImage = "구름많음",
            dayRain = 40,
            nightImage = "비",
            nightRain = 60,
            isDay = true,
            minTemp = 0,
            maxTemp = 5
        ),
        WeeklyWeather(
            day = "월",
            date = "0123",
            dayImage = "이슬비",
            dayRain = 50,
            nightImage = "구름많음",
            nightRain = 40,
            isDay = true,
            minTemp = 4,
            maxTemp = 6
        )
    )

    fun getMockWeeklyWeatherList(): List<WeeklyWeather> = mockWeeklyWeatherList
}