package org.shortweather.presentation.weeklyweather.viewmodel

import androidx.lifecycle.ViewModel
import org.shortweather.data.source.remote.ResponseWeeklyWeather

class WeeklyWeatherViewModel : ViewModel() {
    private val mockWeeklyWeatherList = listOf<ResponseWeeklyWeather?>(
        null,
        ResponseWeeklyWeather(
            weeklyWeatherCalenderWeek = "금", // 요일
            weeklyWeatherCalendarDay = "01.06", // 날짜
            weeklyWeatherDayRain = "70%", //오전 날씨 강수량
            weeklyWeatherNightRain = "70%", //오후 날씨 강수량
            weeklyWeatherTempUp = "-10°", //최고 기온
            weeklyWeatherTempDown = "-10°" // 최저 기온
        ),
        ResponseWeeklyWeather(
            weeklyWeatherCalenderWeek = "금", // 요일
            weeklyWeatherCalendarDay = "01.06", // 날짜
            weeklyWeatherDayRain = "70%", //오전 날씨 강수량
            weeklyWeatherNightRain = "70%", //오후 날씨 강수량
            weeklyWeatherTempUp = "-10°", //최고 기온
            weeklyWeatherTempDown = "-10°" // 최저 기온
        ),
        ResponseWeeklyWeather(
            weeklyWeatherCalenderWeek = "금", // 요일
            weeklyWeatherCalendarDay = "01.06", // 날짜
            weeklyWeatherDayRain = "70%", //오전 날씨 강수량
            weeklyWeatherNightRain = "70%", //오후 날씨 강수량
            weeklyWeatherTempUp = "-10°", //최고 기온
            weeklyWeatherTempDown = "-10°" // 최저 기온
        ),
        ResponseWeeklyWeather(
            weeklyWeatherCalenderWeek = "금", // 요일
            weeklyWeatherCalendarDay = "01.06", // 날짜
            weeklyWeatherDayRain = "70%", //오전 날씨 강수량
            weeklyWeatherNightRain = "70%", //오후 날씨 강수량
            weeklyWeatherTempUp = "-10°", //최고 기온
            weeklyWeatherTempDown = "-10°" // 최저 기온
        )
    )

    fun getMockWeeklyWeatherList(): List<ResponseWeeklyWeather?> = mockWeeklyWeatherList
}