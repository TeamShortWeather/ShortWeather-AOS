package org.shortweather.data.source.remote

import kotlinx.serialization.Serializable

@Serializable
data class ResponseWeeklyWeather(
    val weekly_weather_calender_week: String, // 요일
    val weekly_weather_calendar_day: String, // 날짜
    //val weekly_weather_day_pic: String, //오전 날씨 사진
    val weekly_weather_day_rain: String, //오전 날씨 강수량
    //val weekly_weather_night_pic:String, //오후 날씨 사진
    val weekly_weather_night_rain:String, //오후 날씨 강수량
    val weekly_weather_temp_up: String, //최고 기온
    val weekly_weather_temp_down: String // 최저 기온
)
