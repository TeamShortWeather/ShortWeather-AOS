package org.shortweather.data.repository

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseCustomWeatherRain
import org.shortweather.data.model.ResponseCustomWeatherTemp
import org.shortweather.data.source.remote.CustomWeatherDataSource
import org.shortweather.domain.repository.CustomWeatherRepository
import javax.inject.Inject

class CustomWeatherRepositoryImpl @Inject constructor(
    private val customWeatherDataSource: CustomWeatherDataSource
) :
    CustomWeatherRepository {
    override suspend fun getTemp(): BaseResponse<List<ResponseCustomWeatherTemp>> =
        customWeatherDataSource.getTemp()

    override suspend fun getRain(): BaseResponse<List<ResponseCustomWeatherRain>> =
        customWeatherDataSource.getRain()
}
