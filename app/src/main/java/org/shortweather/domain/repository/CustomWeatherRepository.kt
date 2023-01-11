package org.shortweather.domain.repository

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseCustomWeatherRain
import org.shortweather.data.model.ResponseCustomWeatherTemp

interface CustomWeatherRepository {
    suspend fun getTemp() :
            BaseResponse<List<ResponseCustomWeatherTemp>>
    suspend fun getRain() :
            BaseResponse<List<ResponseCustomWeatherRain>>
}