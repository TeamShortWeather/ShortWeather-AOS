package org.shortweather.domain.repository

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseTodayWeatherInfo
import org.shortweather.data.model.ResponseTodayWeatherToastInfo

interface TodayWeatherRepository {
    suspend fun getTodayWeatherInfo(accessToken: String): BaseResponse<ResponseTodayWeatherInfo>
    suspend fun getTodayWeatherToastInfo(accessToken: String): BaseResponse<ResponseTodayWeatherToastInfo>
}