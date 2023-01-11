package org.shortweather.data.api

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseTodayWeatherInfo
import org.shortweather.data.model.ResponseTodayWeatherToastInfo
import retrofit2.http.GET
import retrofit2.http.Header

interface TodayWeatherService {
    /**
     * 오늘 날씨 조회 API
     */
    @GET("weather/today")
    suspend fun getTodayWeatherInfo(
        @Header("Authorization") accessToken: String
    ): BaseResponse<ResponseTodayWeatherInfo>

    /**
     * 오늘 날씨 toast 조회 API
     */
    @GET("weather/today/question")
    suspend fun getTodayWeatherToastInfo(
        @Header("Authorization") accessToken: String
    ): BaseResponse<ResponseTodayWeatherToastInfo>
}