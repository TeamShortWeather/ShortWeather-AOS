package org.shortweather.data.api

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseCustomWeatherDetail
import org.shortweather.data.model.ResponseCustomWeatherRain
import org.shortweather.data.model.ResponseCustomWeatherTemp
import retrofit2.http.GET
import retrofit2.http.Header

interface CustomWeatherService {
    /**
     * 오늘 날씨 정보 조회 API
     */
    @GET("/weather/today/detail")
    suspend fun getDetail(
        @Header("Authorization") accessToken: String
    ): BaseResponse<ResponseCustomWeatherDetail>

    /**
     * 시간대별 날씨 화면 날씨 조회 API
     */
    @GET("weather/today/detail/temp")
    suspend fun getTemp(): BaseResponse<List<ResponseCustomWeatherTemp>>

    /**
     * 시간대별 날씨 화면 강수 조회 API
     */
    @GET("weather/today/detail/rain")
    suspend fun getRain(): BaseResponse<List<ResponseCustomWeatherRain>>
}
