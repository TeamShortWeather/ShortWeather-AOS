package org.shortweather.data.source.remote

import kotlinx.serialization.Serializable

@Serializable
data class ResponseWeeklyWeather(
    val weeklyWeatherCalenderWeek: String, // 요일
    val weeklyWeatherCalendarDay: String, // 날짜
    //val weekly_weather_day_pic: String, //오전 날씨 사진
    val weeklyWeatherDayRain: String, //오전 날씨 강수량
    //val weekly_weather_night_pic:String, //오후 날씨 사진
    val weeklyWeatherNightRain: String, //오후 날씨 강수량
    val weeklyWeatherTempUp: String, //최고 기온
    val weeklyWeatherTempDown: String // 최저 기온
)
