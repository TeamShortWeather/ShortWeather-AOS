package org.shortweather.data.source.remote

import org.shortweather.data.api.CustomWeatherService
import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseCustomWeatherDetail
import org.shortweather.data.model.ResponseCustomWeatherRain
import org.shortweather.data.model.ResponseCustomWeatherTemp
import javax.inject.Inject

class CustomWeatherRemoteDataSource @Inject constructor(
    private val customWeatherService: CustomWeatherService
) {
    suspend fun getDetail(accessToken: String):
            BaseResponse<ResponseCustomWeatherDetail> = customWeatherService.getDetail(accessToken)

    suspend fun getTemp():
            BaseResponse<List<ResponseCustomWeatherTemp>> = customWeatherService.getTemp()

    suspend fun getRain():
            BaseResponse<List<ResponseCustomWeatherRain>> = customWeatherService.getRain()
}
