package org.shortweather.data.source.remote

import org.shortweather.data.api.TodayWeatherService
import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseTodayWeatherInfo
import org.shortweather.data.model.ResponseTodayWeatherToastInfo
import javax.inject.Inject

class TodayWeatherRemoteDataSource @Inject constructor(
    private val todayWeatherService: TodayWeatherService
) {
    suspend fun getTodayWeatherInfo(accessToken: String): BaseResponse<ResponseTodayWeatherInfo> =
        todayWeatherService.getTodayWeatherInfo(accessToken)

    suspend fun getTodayWeatherToastInfo(accessToken: String): BaseResponse<ResponseTodayWeatherToastInfo> =
        todayWeatherService.getTodayWeatherToastInfo(accessToken)
}