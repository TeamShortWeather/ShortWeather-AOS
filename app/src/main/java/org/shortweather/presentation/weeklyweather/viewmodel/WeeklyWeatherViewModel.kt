package org.shortweather.presentation.weeklyweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.shortweather.data.source.remote.ResponseWeeklyWeather

class WeeklyWeatherViewModel : ViewModel() {
    private val _weeklyResult: MutableLiveData<ResponseWeeklyWeather> = MutableLiveData()
    val weeklyResult: LiveData<ResponseWeeklyWeather>
        get() = _weeklyResult

    val mockWeeklyWeatherList = listOf<ResponseWeeklyWeather?>(
        null,
        ResponseWeeklyWeather(
            weekly_weather_calender_week = "금", // 요일
            weekly_weather_calendar_day = "01.06", // 날짜
            weekly_weather_day_rain = "70%", //오전 날씨 강수량
            weekly_weather_night_rain = "70%", //오후 날씨 강수량
            weekly_weather_temp_up = "-10°", //최고 기온
            weekly_weather_temp_down = "-10°" // 최저 기온
        ),
        ResponseWeeklyWeather(
            weekly_weather_calender_week = "금", // 요일
            weekly_weather_calendar_day = "01.06", // 날짜
            weekly_weather_day_rain = "70%", //오전 날씨 강수량
            weekly_weather_night_rain = "70%", //오후 날씨 강수량
            weekly_weather_temp_up = "-10°", //최고 기온
            weekly_weather_temp_down = "-10°" // 최저 기온
        ),
        ResponseWeeklyWeather(
            weekly_weather_calender_week = "금", // 요일
            weekly_weather_calendar_day = "01.06", // 날짜
            weekly_weather_day_rain = "70%", //오전 날씨 강수량
            weekly_weather_night_rain = "70%", //오후 날씨 강수량
            weekly_weather_temp_up = "-10°", //최고 기온
            weekly_weather_temp_down = "-10°" // 최저 기온
        ),
        ResponseWeeklyWeather(
            weekly_weather_calender_week = "금", // 요일
            weekly_weather_calendar_day = "01.06", // 날짜
            weekly_weather_day_rain = "70%", //오전 날씨 강수량
            weekly_weather_night_rain = "70%", //오후 날씨 강수량
            weekly_weather_temp_up = "-10°", //최고 기온
            weekly_weather_temp_down = "-10°" // 최저 기온
        )
    )
}