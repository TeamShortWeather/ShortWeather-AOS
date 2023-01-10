package org.shortweather.data.repository

import org.shortweather.data.model.BaseResponse
import org.shortweather.data.model.ResponseTodayWeatherInfo
import org.shortweather.data.model.ResponseTodayWeatherToastInfo
import org.shortweather.data.source.remote.TodayWeatherRemoteDataSource
import org.shortweather.domain.repository.TodayWeatherRepository
import javax.inject.Inject

class TodayWeatherRepositoryImpl @Inject constructor(
    private val todayWeatherRemoteDataSource: TodayWeatherRemoteDataSource
) : TodayWeatherRepository {
    override suspend fun getTodayWeatherInfo(accessToken: String): BaseResponse<ResponseTodayWeatherInfo> =
        todayWeatherRemoteDataSource.getTodayWeatherInfo(accessToken)

    override suspend fun getTodayWeatherToastInfo(accessToken: String): BaseResponse<ResponseTodayWeatherToastInfo> =
        todayWeatherRemoteDataSource.getTodayWeatherToastInfo(accessToken)
}